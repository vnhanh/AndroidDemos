package com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login

import android.content.Context
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.vnhanh.base.android.BaseViewModel
import com.vnhanh.common.androidhelper.localData.LocalDb
import com.vnhanh.common.compose.context.getColorResource
import com.vnhanh.common.compose.theme.AppTypography
import com.vnhanh.demo.feature.authentication.R
import com.vnhanh.demo.feature.authentication.domain.validation.AuthenticationFieldValidationUseCase
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.FieldErrorUiModel
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.LoginEmailFieldUiModel
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.LoginPasswordFieldUiModel
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.SubmitAuthUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    appContext: Context,
    savedStateHandle: SavedStateHandle,
    private val validationUseCase: AuthenticationFieldValidationUseCase,
) : BaseViewModel(appContext, savedStateHandle) {
    private val emailLocalDataKey = "login_email_data_key"

    private val _emailFieldData: MutableStateFlow<LoginEmailFieldUiModel> = MutableStateFlow(
        LoginEmailFieldUiModel(
            placeHolderText = appContext.getString(R.string.email_field_placeholder),
            errorData = getDefaultFieldErrorData(),
        )
    )
    val emailFieldData: StateFlow<LoginEmailFieldUiModel> = _emailFieldData.asStateFlow()

    private val _passwordFieldData: MutableStateFlow<LoginPasswordFieldUiModel> = MutableStateFlow(
        LoginPasswordFieldUiModel(
            placeHolderText = appContext.getString(R.string.password_field_placeholder),
            errorData = getDefaultFieldErrorData(),
        ),
    )
    val passwordFieldData: StateFlow<LoginPasswordFieldUiModel> = _passwordFieldData.asStateFlow()

    // true when submitting login at least once
    private var shouldCheckValid = false

    private val _shouldRememberEmail: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val shouldRememberEmail: StateFlow<Boolean> = _shouldRememberEmail.asStateFlow()

    private val _submitSignInState: MutableStateFlow<SubmitAuthUiModel?> = MutableStateFlow(null)
    val submitSignInState: StateFlow<SubmitAuthUiModel?> = _submitSignInState.asStateFlow()

    fun onStart() {
        viewModelScope.launch(Dispatchers.Default) {
            val savedEmail: String =
                LocalDb.getString(emailLocalDataKey, Dispatchers.IO)?.firstOrNull() ?: return@launch
            _emailFieldData.update { data ->
                data.copy(
                    fieldValue = TextFieldValue(text = savedEmail)
                )
            }
        }
    }

    fun updateEmailField(fieldValue: TextFieldValue) {
        val isInValid: Boolean =
            shouldCheckValid && !validationUseCase.isEmailValid(fieldValue.text)

        _emailFieldData.update { data ->
            data.copy(
                fieldValue = fieldValue,
                errorData = data.errorData.copy(
                    error = if (isInValid) appContext.getString(R.string.email_field_error_invalid) else "",
                ),
            )
        }
    }

    fun updatePasswordField(fieldValue: TextFieldValue) {
        val isInValid: Boolean =
            shouldCheckValid && !validationUseCase.isPasswordValid(fieldValue.text)

        _passwordFieldData.update { data ->
            data.copy(
                fieldValue = fieldValue,
                errorData = data.errorData.copy(
                    error = if (isInValid) appContext.getString(R.string.password_field_error_invalid) else "",
                ),
            )
        }
    }

    fun signIn() {
        viewModelScope.launch(Dispatchers.Default) {
            if (!validationUseCase.areEmailAndPasswordValid(
                    email = _emailFieldData.value.text,
                    password = _passwordFieldData.value.text,
                )
            ) {
                // email or password is invalid
                _submitSignInState.update {
                    SubmitAuthUiModel.setFailed()
                }
                return@launch
            }
            _submitSignInState.update { SubmitAuthUiModel.setSubmitting() }
            disableFields()
            saveEmailToLocalDb(_shouldRememberEmail.value)
            shouldCheckValid = true

            delay(1500)
            showBottomToast(R.string.sign_in_failed)

            // focus on email field

            _submitSignInState.update {
                SubmitAuthUiModel.setFailed()
            }
        }
    }

    private fun disableFields() {
        _emailFieldData.update { data -> data.copy(isEnabled = false) }
        _passwordFieldData.update { data -> data.copy(isEnabled = false) }
    }

    fun rememberEmail(isRemember: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _shouldRememberEmail.update { isRemember }
            saveEmailToLocalDb(isRemember)
        }
    }

    private suspend fun saveEmailToLocalDb(shouldRememberEmail: Boolean) {
        val value: String = if (shouldRememberEmail) {
            _emailFieldData.value.fieldValue.text
        } else {
            ""
        }
        LocalDb.saveData(key = emailLocalDataKey, value = value, dispatcher = Dispatchers.IO)
    }

    private fun getDefaultFieldErrorData() = FieldErrorUiModel(
        errorTextStyle = AppTypography.fontSize12LineHeight16Normal.copy(
            textAlign = TextAlign.Start,
            color = appContext.getColorResource(R.color.error),
        ),
    )
}
