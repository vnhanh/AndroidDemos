package com.vnhanh.demo.feature.authentication.presentation.components

import android.content.res.Configuration
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vnhanh.common.compose.modifier.singleClick.singleClick
import com.vnhanh.common.compose.theme.AppTypography
import com.vnhanh.demo.feature.authentication.R
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.SignInViewModel
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.SubmitAuthUiModel
import kotlinx.coroutines.flow.StateFlow


@Composable
internal fun AuthenticationButton(
    buttonLabel: String,
    stateProvider: () -> StateFlow<SubmitAuthUiModel?>,
    onTap: () -> Unit,
) {
    val submitValue: SubmitAuthUiModel? = stateProvider().collectAsStateWithLifecycle().value
    val configuration: Configuration = LocalConfiguration.current
    val transform: Transition<SubmitAuthUiModel?> =
        updateTransition(targetState = submitValue, label = "transform_submit_sign_in")
    val buttonWidth: Dp by transform.animateDp(
        transitionSpec = { tween(400) },
        label = "expand_shrink_button_width"
    ) { state: SubmitAuthUiModel? ->
        when (state?.isLoading) {
            true -> 48.dp
            else -> configuration.screenWidthDp.dp * 0.6f
        }
    }
    val buttonBgColor: Color by transform.animateColor(
        transitionSpec = { tween(400) },
        label = "change_button_bg_color",
    ) { state: SubmitAuthUiModel? ->
        when {
            state?.isLoading == true -> colorResource(id = R.color.sign_in_loading_circular_bg)
            state?.enableSubmit == true -> colorResource(id = R.color.sign_in_bg_enabled)
            else -> colorResource(id = R.color.sign_in_bg_disabled)
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
                .singleClick(
                    isShowClickEffect = true,
                    enabled = submitValue?.enableSubmit == true,
                ) {
                    onTap()
                }
        ) {
            when {
                submitValue?.isLoading == true -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(32.dp),
                        color = colorResource(id = R.color.sign_in_bg_enabled),
                        trackColor = colorResource(id = R.color.sign_in_loading_circular_track)
                    )
                }

                else -> {
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
            }
        }
    }
}

@Composable
internal fun AuthenticationButton(
    buttonLabel: String,
    viewModel: SignInViewModel,
    onTap: () -> Unit,
) {
    val submitValue: SubmitAuthUiModel? =
        viewModel.submitSignInState.collectAsStateWithLifecycle().value
    val configuration: Configuration = LocalConfiguration.current
    val transform: Transition<SubmitAuthUiModel?> =
        updateTransition(targetState = submitValue, label = "transform_submit_sign_in")
    val buttonWidth: Dp by transform.animateDp(
        transitionSpec = { tween(400) },
        label = "expand_shrink_button_width"
    ) { state: SubmitAuthUiModel? ->
        when (state?.isLoading) {
            true -> 48.dp
            else -> configuration.screenWidthDp.dp * 0.6f
        }
    }
    val buttonBgColor: Color by transform.animateColor(
        transitionSpec = { tween(400) },
        label = "change_button_bg_color",
    ) { state: SubmitAuthUiModel? ->
        when {
            state?.isLoading == true -> colorResource(id = R.color.sign_in_loading_circular_bg)
            state?.enableSubmit == true -> colorResource(id = R.color.sign_in_bg_enabled)
            else -> colorResource(id = R.color.sign_in_bg_disabled)
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
                .singleClick(
                    isShowClickEffect = true,
                    enabled = submitValue?.enableSubmit == true,
                ) {
                    onTap()
                }
        ) {
            when {
                submitValue?.isLoading == true -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(32.dp),
                        color = colorResource(id = R.color.sign_in_bg_enabled),
                        trackColor = colorResource(id = R.color.sign_in_loading_circular_track)
                    )
                }

                else -> {
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
            }
        }
    }
}
