package com.vnhanh.demo.feature.authentication.presentation.login

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vnhanh.common.compose.modifier.fillMaxWidthLayoutResponsive
import com.vnhanh.common.compose.textfield.AppTextField
import com.vnhanh.demo.feature.authentication.presentation.login.model.LoginEmailFieldUiModel
import com.vnhanh.demo.feature.authentication.presentation.login.model.LoginPasswordFieldUiModel

/**
 * Enter email + password
 * Remember email + forgot password
 * Login by biometric
 * Save password after encode it
 * Allow login via Facebook, Google and Twitter account
 */
@Composable
internal fun LoginComposable(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel,
) {
    Row(modifier.padding(horizontal = 16.dp)) {
        EmailField(
            modifier = Modifier.fillMaxWidthLayoutResponsive(),
            loginViewModel = loginViewModel,
        )
        Spacer(modifier = Modifier.height(24.dp))
        PasswordField(
            modifier = Modifier.fillMaxWidthLayoutResponsive(),
            loginViewModel = loginViewModel,
        )
        Spacer(modifier = Modifier.height(8.dp))
        RememberAndForgotPassword()
        Spacer(modifier = Modifier.height(36.dp))
        LoginButton()
        Spacer(modifier = Modifier.height(36.dp))
        SocialLoginButtons()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun EmailField(
    modifier: Modifier,
    loginViewModel: LoginViewModel,
) {
    val emailFieldData: LoginEmailFieldUiModel by loginViewModel.emailFieldData.collectAsStateWithLifecycle()

    AppTextField(
        value = emailFieldData.fieldValue,
        modifier = modifier,
        textColor = emailFieldData.textColorValue,
        placeHolderText = emailFieldData.placeHolderText,
        placeHolderColor = emailFieldData.placeHolderColorValue,
        focusColor = emailFieldData.focusColorValue,
        unFocusColor = emailFieldData.unFocusColorValue,
        cursorColor = emailFieldData.cursorColorValue,
        onValueChanged = { textFieldValue -> loginViewModel.updateEmailField(textFieldValue) },
    )
}

@Composable
private fun PasswordField(
    modifier: Modifier,
    loginViewModel: LoginViewModel,
) {
    val passwordFieldData: LoginPasswordFieldUiModel by loginViewModel.passwordFieldData.collectAsStateWithLifecycle()

    AppTextField(
        value = passwordFieldData.fieldValue,
        modifier = modifier,
        textColor = passwordFieldData.textColorValue,
        placeHolderText = passwordFieldData.placeHolderText,
        placeHolderColor = passwordFieldData.placeHolderColorValue,
        focusColor = passwordFieldData.focusColorValue,
        unFocusColor = passwordFieldData.unFocusColorValue,
        cursorColor = passwordFieldData.cursorColorValue,
        onValueChanged = { textFieldValue -> loginViewModel.updatePasswordField(textFieldValue) },
    )
}

@Composable
private fun RememberAndForgotPassword(

) {

}

@Composable
private fun LoginButton() {

}

@Composable
private fun SocialLoginButtons() {

}
