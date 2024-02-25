package com.alanvo.androiddemo

import android.os.CountDownTimer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnhanh.common.androidhelper.AndroidConstants
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
import org.koin.java.KoinJavaComponent.inject
import timber.log.Timber

class MainViewModel : ViewModel() {
    private val savedStateHandle: SavedStateHandle by inject(SavedStateHandle::class.java)

    @OptIn(ExperimentalCoroutinesApi::class)
    val appToastState: StateFlow<AppToastUiModel?> =
        savedStateHandle.getStateFlow(AndroidConstants.keyEventAppToast, null)
            .flatMapLatest { toastData: AppToastUiModel? ->
                flow {
                    if (toastData != null) {
                        restartCountDownTimer()
                    }
                    emit(toastData)
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
            savedStateHandle[AndroidConstants.keyEventAppToast] = ""
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
