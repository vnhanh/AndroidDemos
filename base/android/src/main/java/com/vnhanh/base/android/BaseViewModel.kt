package com.vnhanh.base.android

import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnhanh.common.androidhelper.AndroidConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    protected val appContext: Context,
    protected val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    open fun isInternetAvailable(): Boolean = true

    private val _logoutState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val logoutState: StateFlow<Boolean> = _logoutState.asStateFlow()

    fun showBottomToast(@StringRes msgId: Int) {
        viewModelScope.launch {
            savedStateHandle[AndroidConstants.keyEventAppToast] = appContext.getString(msgId)
        }
    }

    fun showBottomToast(message: String) {
        viewModelScope.launch {
            savedStateHandle[AndroidConstants.keyEventAppToast] = message
        }
    }

}
