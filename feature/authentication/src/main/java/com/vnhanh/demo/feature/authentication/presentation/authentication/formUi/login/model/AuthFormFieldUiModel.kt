package com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class LoginEmailFieldUiModel(
    override val fieldValue: TextFieldValue = TextFieldValue(),
    val isEnabled: Boolean = true,
    val shouldFocus: Boolean = false,
    val textColorValue: Color = Color.Black,
    val placeHolderText: String = "email",
    val placeHolderColorValue: Color = Color.Gray,
    val focusColorValue: Color = Color.Black,
    val unFocusColorValue: Color = Color.LightGray,
    val cursorColorValue: Color = Color.Black,
    @DrawableRes val trailingIconResId: Int? = null,
    override val errorData: FieldErrorUiModel = FieldErrorUiModel(),
) : IFieldErrorData, IFieldValue

data class LoginPasswordFieldUiModel(
    override val fieldValue: TextFieldValue = TextFieldValue(),
    val isEnabled: Boolean = true,
    val shouldFocus: Boolean = false,
    val textColorValue: Color = Color.Black,
    val placeHolderText: String = "email",
    val placeHolderColorValue: Color = Color.Gray,
    val focusColorValue: Color = Color.Black,
    val unFocusColorValue: Color = Color.LightGray,
    val cursorColorValue: Color = Color.Black,
    @DrawableRes val trailingIconResId: Int? = null,
    val isVisible: Boolean = false,
    override val errorData: FieldErrorUiModel = FieldErrorUiModel(),
) : IFieldErrorData, IFieldValue

interface IFieldValue {
    val fieldValue: TextFieldValue

    val text: String
        get() = fieldValue.text
}

interface IFieldErrorData {
    val errorData: FieldErrorUiModel

    val errorText: String
        get() = errorData.error

    val hasError: Boolean
        get() = errorData.error.isNotBlank()

    val errorTextStyle: TextStyle
        get() = errorData.errorTextStyle

    val errorPaddingTop: Dp
        get() = errorData.spaceBetweenFieldAndError
}

data class FieldErrorUiModel(
    val error: String = "",
    val spaceBetweenFieldAndError: Dp = 4.dp,
    val errorTextStyle: TextStyle = TextStyle.Default,
    val maxLines: Int = 1,
    val overflow: TextOverflow = TextOverflow.Ellipsis,
)
