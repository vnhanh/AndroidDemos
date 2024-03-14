package com.vnhanh.demo.feature.authentication.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vnhanh.common.compose.modifier.singleClick.singleClick
import com.vnhanh.common.compose.textfield.AppTextField
import com.vnhanh.common.compose.textfield.TrailIcon
import com.vnhanh.common.compose.theme.AppTypography
import com.vnhanh.demo.feature.authentication.R
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

    AppTextField(
        value = emailFieldData.fieldValue,
        modifier = modifier,
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

    Box(modifier = modifier) {
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
}

@Composable
internal fun AuthenticationButton(
    buttonLabel: String,
    onTap: () -> Unit
) {
    val configuration = LocalConfiguration.current
    var isIdle by remember { mutableStateOf(true) }
    val transform: Transition<Boolean> =
        updateTransition(targetState = isIdle, label = "transform_submit_sign_in")
    val buttonWidth: Dp by transform.animateDp(
        transitionSpec = { tween(400) },
        label = "expand_shrink_button_width"
    ) { state ->
        if (state) {
            configuration.screenWidthDp.dp * 0.6f
        } else {
            48.dp
        }
    }
    val buttonBgColor by transform.animateColor(
        transitionSpec = { tween(400) },
        label = "change_button_bg_color",
    ) { state ->
        if (state) {
            colorResource(id = R.color.sign_in_bg)
        } else {
            colorResource(id = R.color.sign_in_loading_circular_bg)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .width(buttonWidth)
                .height(48.dp)
                .clip(CircleShape)
                .background(color = buttonBgColor, shape = CircleShape)
                .singleClick(isShowClickEffect = true) {
                    // signing in
                    isIdle = false
                    onTap()
                }
        ) {
            when (isIdle) {
                true -> {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = buttonLabel,
                        style = AppTypography.fontSize16LineHeight22Bold.copy(
                            color = colorResource(id = R.color.sign_in_text),
                            textAlign = TextAlign.Center,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                false -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(32.dp),
                        color = colorResource(id = R.color.sign_in_bg),
                        trackColor = colorResource(id = R.color.sign_in_loading_circular_track)
                    )
                }
            }
        }
    }
}