package com.vnhanh.common.compose.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.DialogProperties

/**
 * @param delayVisibleTime: delayed time until starting animation
 */
@Composable
fun DialogAnimatedVisibility(
    dialogVisibleProvider: () -> Boolean,
    isDismissOnBackPress: Boolean = true,
    isDismissOnClickOutside: Boolean = true,
    delayVisibleTime: Int = 10,
    showingDialogContentAnimTime: Int,
    hidingDialogContentAnimTime: Int,
    onDismissListener: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val dialogAnimation by animateIntAsState(
        targetValue = if (dialogVisibleProvider()) showingDialogContentAnimTime else 0,
        animationSpec = if (dialogVisibleProvider()) tween(showingDialogContentAnimTime) else tween(
            hidingDialogContentAnimTime
        ),
        label = "animation_state_show_hide_dialog",
    )

    if (dialogAnimation > 0) {
        androidx.compose.ui.window.Dialog(
            onDismissRequest = { onDismissListener?.invoke() },
            properties = DialogProperties(
                dismissOnBackPress = isDismissOnBackPress,
                dismissOnClickOutside = isDismissOnClickOutside,
                usePlatformDefaultWidth = false
            )
        ) {
            /**
             * show dialog content after about 10ms showing dialog
             * start animation hiding dialog content immediately when receiving requesting hide dialog via @param [dialogVisibleProvider]
             */
            AnimatedVisibility(
                visible = if (dialogVisibleProvider()) dialogAnimation >= delayVisibleTime else false,
                enter = fadeIn(animationSpec = tween(showingDialogContentAnimTime)) + scaleIn(
                    animationSpec = tween(showingDialogContentAnimTime),
                    initialScale = 0.2f
                ),
                exit = fadeOut(animationSpec = tween(hidingDialogContentAnimTime)) + scaleOut(
                    animationSpec = tween(hidingDialogContentAnimTime),
                    targetScale = 0.2f
                ),
                label = "animation_show_hide_dialog"
            ) {
                content()
            }
        }
    }
}
