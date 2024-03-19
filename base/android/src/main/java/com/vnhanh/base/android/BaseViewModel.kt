package com.vnhanh.base.android

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnhanh.common.androidhelper.AndroidConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    open fun isInternetAvailable(): Boolean = true

    private val _logoutState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val logoutState: StateFlow<Boolean> = _logoutState.asStateFlow()

    fun showBottomToast(title: String = "", message: String = "") {
        viewModelScope.launch(Dispatchers.Default) {
            savedStateHandle[AndroidConstants.keyEventAppToast] = ToastUiModel(
                title = title,
                message = message,
                position = TOAST_POSITION_BOTTOM,
            )
        }
    }

    fun showTopToast(title: String = "", message: String) {
        viewModelScope.launch(Dispatchers.Default) {
            savedStateHandle[AndroidConstants.keyEventAppToast] = ToastUiModel(
                title = title,
                message = message,
                position = TOAST_POSITION_TOP,
            )
        }
    }

}
