package com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login

import android.content.Context
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.vnhanh.base.android.BaseViewModel
import com.vnhanh.demo.feature.authentication.R
import com.vnhanh.demo.feature.authentication.domain.validation.AuthenticationFieldValidationUseCase
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.LoginEmailFieldUiModel
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.LoginPasswordFieldUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val appContext: Context,
    private val validationUseCase: AuthenticationFieldValidationUseCase,
) : BaseViewModel() {
    private val _emailFieldData: MutableStateFlow<LoginEmailFieldUiModel> = MutableStateFlow(
        LoginEmailFieldUiModel(
            placeHolderText = appContext.getString(R.string.email_field_placeholder)
        )
    )
    val emailFieldData: StateFlow<LoginEmailFieldUiModel> = _emailFieldData.asStateFlow()

    private val _passwordFieldData: MutableStateFlow<LoginPasswordFieldUiModel> = MutableStateFlow(
        LoginPasswordFieldUiModel(
            placeHolderText = appContext.getString(R.string.password_field_placeholder)
        )
    )
    val passwordFieldData: StateFlow<LoginPasswordFieldUiModel> = _passwordFieldData.asStateFlow()

    fun updateEmailField(fieldValue: TextFieldValue) {
        val isInValid: Boolean = !validationUseCase.isEmailValid(fieldValue.text)
        _emailFieldData.update { data ->
            data.copy(
                fieldValue = fieldValue,
                error = if (isInValid) appContext.getString(R.string.email_field_error_invalid) else null,
            )
        }
    }

    fun updatePasswordField(fieldValue: TextFieldValue) {
        val isInValid: Boolean = !validationUseCase.isPasswordValid(fieldValue.text)
        _passwordFieldData.update { data ->
            data.copy(
                fieldValue = fieldValue,
                error = if (isInValid) appContext.getString(R.string.password_field_error_invalid) else null,
            )
        }
    }

    fun signIn() {
        viewModelScope.launch(Dispatchers.Default) {
            // TODO:
        }
    }

    fun rememberEmail(isRemember: Boolean) {

    }
}
