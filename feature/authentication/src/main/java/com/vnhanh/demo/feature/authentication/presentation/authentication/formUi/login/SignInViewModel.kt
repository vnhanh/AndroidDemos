package com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login

import android.content.Context
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewModelScope
import com.vnhanh.base.android.BaseViewModel
import com.vnhanh.common.compose.context.getColorResource
import com.vnhanh.common.compose.theme.AppTypography
import com.vnhanh.demo.feature.authentication.R
import com.vnhanh.demo.feature.authentication.domain.validation.AuthenticationFieldValidationUseCase
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.FieldErrorUiModel
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.LoginEmailFieldUiModel
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.LoginPasswordFieldUiModel
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.SubmitSignInUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class SignInViewModel(
    private val appContext: Context,
    private val validationUseCase: AuthenticationFieldValidationUseCase,
) : BaseViewModel() {
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

    private val _submitSignInState: MutableStateFlow<SubmitSignInUiModel?> = MutableStateFlow(null)
    val submitSignInState: StateFlow<SubmitSignInUiModel?> = _submitSignInState.asStateFlow()

    fun updateEmailField(fieldValue: TextFieldValue) {
        val isInValid: Boolean =
            shouldCheckValid && !validationUseCase.isEmailValid(fieldValue.text)
        Timber.tag("TestAlan").d("is email invalid $isInValid")
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
        Timber.tag("TestAlan").d("is password invalid $isInValid")
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
            shouldCheckValid = true
            _emailFieldData.update { data ->
                data.copy(
                    isEnabled = false,
                )
            }
            delay(2000)
            _submitSignInState.update { data ->
                data?.copy(
                    isSuccess = false,
                    error = appContext.getString(R.string.sign_in_failed)
                )
            }
        }
    }

    fun rememberEmail(isRemember: Boolean) {

    }

    private fun getDefaultFieldErrorData() = FieldErrorUiModel(
        errorTextStyle = AppTypography.fontSize12LineHeight16Normal.copy(
            textAlign = TextAlign.Start,
            color = appContext.getColorResource(R.color.error),
        ),
    )
}
