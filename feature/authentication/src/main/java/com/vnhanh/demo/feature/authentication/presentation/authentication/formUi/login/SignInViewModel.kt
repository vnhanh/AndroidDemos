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
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.LoginRememberEmailUiModel
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.SubmitAuthUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class SignInViewModel(
    private val appContext: Context,
    savedStateHandle: SavedStateHandle,
    private val validationUseCase: AuthenticationFieldValidationUseCase,
) : BaseViewModel(savedStateHandle) {
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

    private val _shouldRememberEmail: MutableStateFlow<LoginRememberEmailUiModel> =
        MutableStateFlow(LoginRememberEmailUiModel())
    val shouldRememberEmail: StateFlow<LoginRememberEmailUiModel> =
        _shouldRememberEmail.asStateFlow()

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

    fun updateEmailField(fieldValue: TextFieldValue, shouldCheckValidOfSubmit: Boolean = true) {
        viewModelScope.launch {
            val shouldShowError: Boolean =
                shouldCheckValid && !validationUseCase.isEmailValid(fieldValue.text)

            _emailFieldData.update { data ->
                Timber.d("TestAlan - view model - update email field")
                data.copy(
                    fieldValue = fieldValue,
                    errorData = data.errorData.copy(
                        error = if (shouldShowError) appContext.getString(R.string.email_field_error_invalid) else "",
                    ),
                )
            }

            if (shouldCheckValidOfSubmit) checkValid()
        }
    }

    private fun checkIfEmailValid() {
        updateEmailField(
            fieldValue = _emailFieldData.value.fieldValue,
            shouldCheckValidOfSubmit = false
        )
    }

    fun updatePasswordField(fieldValue: TextFieldValue, shouldCheckValidOfSubmit: Boolean = true) {
        viewModelScope.launch {
            val shouldShowError: Boolean =
                shouldCheckValid && !validationUseCase.isPasswordValid(fieldValue.text)
            _passwordFieldData.update { data ->
                data.copy(
                    fieldValue = fieldValue,
                    errorData = data.errorData.copy(
                        error = if (shouldShowError) appContext.getString(R.string.password_field_error_invalid) else "",
                    ),
                )
            }

            if (shouldCheckValidOfSubmit) checkValid()
        }
    }

    private fun checkIfPassWordValid() {
        updatePasswordField(
            fieldValue = _passwordFieldData.value.fieldValue,
            shouldCheckValidOfSubmit = false
        )
    }

    private fun checkValid() {
        viewModelScope.launch {
            val areFieldValid = validationUseCase.areEmailAndPasswordValid(
                email = _emailFieldData.value.text,
                password = _passwordFieldData.value.text,
            )
            _submitSignInState.update {
                SubmitAuthUiModel.setIdle(enableSubmit = areFieldValid)
            }
        }
    }

    fun signIn() {
        viewModelScope.launch(Dispatchers.Default) {
            shouldCheckValid = true
            checkIfEmailValid()
            checkIfPassWordValid()
            if (!validationUseCase.areEmailAndPasswordValid(
                    email = _emailFieldData.value.text,
                    password = _passwordFieldData.value.text,
                )
            ) {
                // email or password is invalid
                _submitSignInState.update {
                    SubmitAuthUiModel.failed()
                }
                return@launch
            }
            _submitSignInState.update {
                SubmitAuthUiModel.loading()
            }
            disableFields()
            saveEmailToLocalDbIfRemember(_shouldRememberEmail.value.shouldRemember)

            delay(10000)

            showTopToast(message = appContext.getString(R.string.sign_in_failed))

            // focus on email field
            _emailFieldData.update { data ->
                data.copy(
                    shouldFocus = true,
                    isEnabled = true
                )
            }
            _passwordFieldData.update { data ->
                data.copy(
                    isEnabled = true,
                )
            }
            _submitSignInState.update {
                SubmitAuthUiModel.failed()
            }
        }
    }

    private fun disableFields() {
        _emailFieldData.update { data -> data.copy(isEnabled = false) }
        _passwordFieldData.update { data -> data.copy(isEnabled = false) }
    }

    fun rememberEmail(shouldRemember: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _shouldRememberEmail.update { data ->
                data.copy(shouldRemember = shouldRemember)
            }
            saveEmailToLocalDbIfRemember(shouldRemember)
        }
    }

    private suspend fun saveEmailToLocalDbIfRemember(shouldRememberEmail: Boolean) {
        val value: String = if (shouldRememberEmail) {
            _emailFieldData.value.fieldValue.text
        } else {
            ""
        }
        LocalDb.saveData(key = emailLocalDataKey, value = value, dispatcher = Dispatchers.IO)
    }

    private fun getDefaultFieldErrorData(error: String = "") = FieldErrorUiModel(
        error = error,
        errorTextStyle = AppTypography.fontSize12LineHeight16Normal.copy(
            textAlign = TextAlign.Start,
            color = appContext.getColorResource(R.color.error),
        ),
    )
}
