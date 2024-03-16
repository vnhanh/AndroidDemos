package com.vnhanh.demo.feature.authentication.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vnhanh.common.compose.modifier.singleClick.singleClick
import com.vnhanh.common.compose.textfield.AppTextField
import com.vnhanh.common.compose.textfield.TrailIcon
import com.vnhanh.common.compose.theme.AppTypography.fontSize13LineHeight18Normal
import com.vnhanh.demo.feature.authentication.R
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.IFieldErrorData
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.LoginEmailFieldUiModel
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.LoginPasswordFieldUiModel
import kotlinx.coroutines.flow.StateFlow


@Composable
internal fun EmailField(
    modifier: Modifier,
    fieldDataProvider: () -> StateFlow<LoginEmailFieldUiModel>,
    onFieldValueChanged: (TextFieldValue) -> Unit,
) {
    val emailFieldData: LoginEmailFieldUiModel by fieldDataProvider().collectAsStateWithLifecycle()

    Column(modifier = modifier) {
        AppTextField(
            value = emailFieldData.fieldValue,
            modifier = modifier,
            textStyle = fontSize13LineHeight18Normal,
            textColor = emailFieldData.textColorValue,
            placeHolderText = emailFieldData.placeHolderText,
            placeHolderColor = emailFieldData.placeHolderColorValue,
            focusColor = emailFieldData.focusColorValue,
            unFocusColor = emailFieldData.unFocusColorValue,
            cursorColor = emailFieldData.cursorColorValue,
            trailingComposable = {
                TrailIcon(
                    iconResId = R.drawable.ic_email_dp24,
                    tintColor = colorResource(id = R.color.authentication_secondary),
                    contentDescription = "Email Icon",
                )
            },
            onValueChanged = { textFieldValue -> onFieldValueChanged(textFieldValue) },
        )

        FieldErrorWithTransition(
            fieldErrorData = emailFieldData
        )
    }
}

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
internal fun PasswordField(
    modifier: Modifier,
    fieldDataProvider: () -> StateFlow<LoginPasswordFieldUiModel>,
    onFieldValueChanged: (TextFieldValue) -> Unit,
) {
    val passwordFieldData: LoginPasswordFieldUiModel by fieldDataProvider().collectAsStateWithLifecycle()
    val animShowHide = AnimatedImageVector.animatedVectorResource(id = R.drawable.anim_show_hide)
    var atEnd by remember { mutableStateOf(false) }
    val passwordTransformation = remember { PasswordVisualTransformation() }

    Column(modifier = modifier) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AnimatedContent(
                targetState = atEnd,
                transitionSpec = { fadeIn(tween(400)) togetherWith fadeOut(tween(400)) },
                label = "transform_show_hide_password",
            ) { passwordVisible ->
                AppTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = passwordFieldData.fieldValue,
                    textColor = passwordFieldData.textColorValue,
                    placeHolderText = passwordFieldData.placeHolderText,
                    placeHolderColor = passwordFieldData.placeHolderColorValue,
                    focusColor = passwordFieldData.focusColorValue,
                    unFocusColor = passwordFieldData.unFocusColorValue,
                    cursorColor = passwordFieldData.cursorColorValue,
                    keyboardType = KeyboardType.Password,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else passwordTransformation,
                    onValueChanged = { textFieldValue -> onFieldValueChanged(textFieldValue) },
                )
            }

            // show/hide password icon with animation
            TrailIcon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp)
                    .clip(CircleShape)
                    .singleClick {
                        atEnd = !atEnd
                    },
                painter = rememberAnimatedVectorPainter(
                    animatedImageVector = animShowHide,
                    atEnd = atEnd,
                ),
                tintColor = colorResource(id = R.color.authentication_secondary),
                contentDescription = "Email Icon",
            )
        }

        FieldErrorWithTransition(
            fieldErrorData = passwordFieldData
        )
    }
}

@Composable
private fun FieldErrorWithTransition(
    fieldErrorData: IFieldErrorData
) {
    AnimatedContent(
        targetState = fieldErrorData.hasError,
        transitionSpec = {
            (
                    fadeIn(tween(400)) + expandVertically(tween(400)) { -it }
                    )
                .togetherWith(
                    fadeOut(tween(400)) + shrinkVertically(tween(400)) { -it }
                )
        },
        label = "transition_show_hide_field_error"
    ) { hasErrorState: Boolean ->
        when (hasErrorState) {
            true -> {
                Text(
                    modifier = Modifier.padding(top = fieldErrorData.errorPaddingTop),
                    text = fieldErrorData.errorText,
                    style = fieldErrorData.errorTextStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            false -> Unit
        }
    }
}
