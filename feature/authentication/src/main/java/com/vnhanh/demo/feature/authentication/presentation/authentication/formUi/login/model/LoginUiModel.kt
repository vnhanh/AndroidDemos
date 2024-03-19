package com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model


data class SubmitAuthUiModel(
    val state: Int = STATE_IDLE,
    val enableSubmit: Boolean = false,
) {
    val isLoading: Boolean
        get() = state == STATE_SUBMITTING

    val isSuccess: Boolean
        get() = state == STATE_SUCCESS

    val isFailed: Boolean
        get() = state == STATE_FAILED

    val isIdle: Boolean
        get() = state == STATE_IDLE


    companion object {
        private const val STATE_IDLE = 0
        private const val STATE_SUBMITTING = 1
        private const val STATE_SUCCESS = 2
        private const val STATE_FAILED = 3

        fun failed() = SubmitAuthUiModel(state = STATE_FAILED, enableSubmit = false)

        fun setSuccess() = SubmitAuthUiModel(state = STATE_FAILED, enableSubmit = false)

        fun setIdle(enableSubmit: Boolean) =
            SubmitAuthUiModel(state = STATE_IDLE, enableSubmit = enableSubmit)

        fun loading() = SubmitAuthUiModel(state = STATE_SUBMITTING, enableSubmit = false)
    }
}

data class LoginRememberEmailUiModel(
    val shouldRemember: Boolean = false,
    val enabled: Boolean = true,
)
