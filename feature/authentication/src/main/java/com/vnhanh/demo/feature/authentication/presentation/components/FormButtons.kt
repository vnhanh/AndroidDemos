package com.vnhanh.demo.feature.authentication.presentation.components

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.model.SubmitAuthUiModel
import kotlinx.coroutines.flow.StateFlow


@Composable
internal fun AuthenticationButton(
    buttonLabel: String,
    stateProvider: () -> StateFlow<SubmitAuthUiModel?>,
    onTap: () -> Unit,
) {
    val submitValue by stateProvider().collectAsStateWithLifecycle()
    val configuration = LocalConfiguration.current
    var isIdle by remember(submitValue) { mutableStateOf(submitValue?.isLoading != true) }
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