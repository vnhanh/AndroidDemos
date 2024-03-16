package com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.register

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
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.SubmitAuthUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
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
        )
    )
    val passwordFieldData: StateFlow<LoginPasswordFieldUiModel> = _passwordFieldData.asStateFlow()

    private val _confirmPasswordFieldData: MutableStateFlow<LoginPasswordFieldUiModel> =
        MutableStateFlow(
            LoginPasswordFieldUiModel(
                placeHolderText = appContext.getString(R.string.confirm_password_field_placeholder)
            )
        )
    val confirmPasswordFieldData: StateFlow<LoginPasswordFieldUiModel> =
        _confirmPasswordFieldData.asStateFlow()

    private val _submitSignUpInState: MutableStateFlow<SubmitAuthUiModel?> = MutableStateFlow(null)
    val submitSignUpInState: StateFlow<SubmitAuthUiModel?> = _submitSignUpInState.asStateFlow()

    fun updateEmailField(fieldValue: TextFieldValue) {
        val isInValid: Boolean = !validationUseCase.isEmailValid(fieldValue.text)
        _emailFieldData.update { data ->
            data.copy(
                fieldValue = fieldValue,
                errorData = data.errorData.copy(
                    error = if (isInValid) appContext.getString(R.string.email_field_error_invalid) else ""
                ),
            )
        }
    }

    fun updatePasswordField(fieldValue: TextFieldValue) {
        val isInValid: Boolean = !validationUseCase.isPasswordValid(fieldValue.text)
        _passwordFieldData.update { data ->
            data.copy(
                fieldValue = fieldValue,
                errorData = data.errorData.copy(
                    error = if (isInValid) appContext.getString(R.string.password_field_error_invalid) else "",
                ),
            )
        }
    }

    fun register() {
        viewModelScope.launch(Dispatchers.Default) {

        }
    }


    /*----------------------------------COMMON----------------------------------*/
    private fun getDefaultFieldErrorData() = FieldErrorUiModel(
        errorTextStyle = AppTypography.fontSize12LineHeight16Normal.copy(
            textAlign = TextAlign.Start,
            color = appContext.getColorResource(R.color.error),
        ),
    )
}
