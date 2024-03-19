package com.alanvo.androiddemo.presentation

import android.content.Context
import android.os.CountDownTimer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnhanh.base.android.TOAST_POSITION_BOTTOM
import com.vnhanh.base.android.TOAST_POSITION_TOP
import com.vnhanh.base.android.ToastUiModel
import com.vnhanh.common.androidhelper.AndroidConstants
import com.vnhanh.common.compose.toast.AppToast
import com.vnhanh.common.compose.toast.AppToastUiModel
import com.vnhanh.common.log.printDebugStackTrace
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val appContext: Context,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val appToastState: StateFlow<AppToastUiModel?> =
        savedStateHandle.getStateFlow(AndroidConstants.keyEventAppToast, null)
            .flatMapLatest { toastData: ToastUiModel? ->
                flow {
                    if (toastData != null) {
                        restartCountDownTimer()
                    }
                    val toastUiData =
                        AppToastUiModel.getDefault(
                            title = toastData?.title,
                            message = toastData?.message,
                            positionType = if (toastData?.position == TOAST_POSITION_TOP) AppToast.TOP else AppToast.BOTTOM
                        )
                    emit(toastUiData)
                }
            }
            .catch {
                Timber.e("MainViewModel - catch exception while collecting bottom toast ${it.message}")
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )

    private val timeShowToast = 3000L

    private val countDownTimer = object : CountDownTimer(timeShowToast, timeShowToast) {
        override fun onTick(millisUntilFinished: Long) {}

        override fun onFinish() {
            hideBottomToast()
        }
    }

    fun hideBottomToast() {
        viewModelScope.launch {
            savedStateHandle[AndroidConstants.keyEventAppToast] = ToastUiModel(
                position = appToastState.value?.positionType ?: TOAST_POSITION_BOTTOM
            )
        }
    }

    private fun restartCountDownTimer() {
        viewModelScope.launch {
            try {
                countDownTimer.cancel()
                if (isActive) countDownTimer.start()
            } catch (e: Exception) {
                e.printDebugStackTrace()
            }
        }
    }

    override fun onCleared() {
        try {
            countDownTimer.cancel()
        } catch (e: Exception) {
            e.printDebugStackTrace()
        }
        super.onCleared()
    }
}
