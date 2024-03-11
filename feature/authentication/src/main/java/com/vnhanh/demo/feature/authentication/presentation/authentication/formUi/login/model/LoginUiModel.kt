package com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue

data class LoginEmailFieldUiModel(
    val fieldValue: TextFieldValue = TextFieldValue(),
    val textColorValue: Color = Color.Black,
    val placeHolderText: String = "email",
    val placeHolderColorValue: Color = Color.Gray,
    val focusColorValue: Color = Color.Black,
    val unFocusColorValue: Color = Color.LightGray,
    val cursorColorValue: Color = Color.Black,
    @DrawableRes val trailingIconResId: Int? = null,
    val error: String? = null,
)

data class LoginPasswordFieldUiModel(
    val fieldValue: TextFieldValue = TextFieldValue(),
    val textColorValue: Color = Color.Black,
    val placeHolderText: String = "email",
    val placeHolderColorValue: Color = Color.Gray,
    val focusColorValue: Color = Color.Black,
    val unFocusColorValue: Color = Color.LightGray,
    val cursorColorValue: Color = Color.Black,
    @DrawableRes val trailingIconResId: Int? = null,
    val isVisible: Boolean = false,
    val error: String? = null,
)
