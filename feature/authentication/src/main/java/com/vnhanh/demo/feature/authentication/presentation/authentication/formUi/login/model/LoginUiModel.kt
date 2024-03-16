package com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model


data class SubmitAuthUiModel(
    val state: Int = STATE_IDLE,
    val error: String = "",
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

        fun setFailed(
            error: String
        ) = SubmitAuthUiModel(state = STATE_FAILED, error = error)

        fun setSuccess() = SubmitAuthUiModel(state = STATE_FAILED, error = "")

        fun setIdle() = SubmitAuthUiModel(state = STATE_IDLE)

        fun setSubmitting() = SubmitAuthUiModel(state = STATE_SUBMITTING)
    }
}
