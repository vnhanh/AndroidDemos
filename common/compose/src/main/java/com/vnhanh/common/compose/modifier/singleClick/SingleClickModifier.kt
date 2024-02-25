package com.vnhanh.common.compose.modifier.singleClick

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role
import com.vnhanh.common.compose.ComposeConstant.THRESHOLD_TIME_DEBOUNCE_CLICK_MILLISECOND

fun Modifier.singleClick(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    thresholdTimeDebounceClick: Long = THRESHOLD_TIME_DEBOUNCE_CLICK_MILLISECOND,
    isShowClickEffect: Boolean = true,
    interactionSource: MutableInteractionSource? = null, //pass instance MutableInteractionSource(), not pass remember { MutableInteractionSource() }
    indication: Indication? = null,
    isUsingForLazyItem: Boolean = false,
    onClick: () -> Unit,
): Modifier = composed {
    val localIndication = LocalIndication.current
    val singleClickEventListener = if (isUsingForLazyItem) {
        SingleClickEventListener.get(thresholdTimeDebounceClick)
    } else {
        remember { SingleClickEventListener.get(thresholdTimeDebounceClick) }
    }
    val currentIndication = remember {
        if (isShowClickEffect) {
            indication ?: localIndication
        } else {
            null
        }
    }
    val currentInteractionSource = remember {
        if (isShowClickEffect) {
            interactionSource ?: MutableInteractionSource()
        } else {
            NoRippleInteractionSource()
        }
    }

    this then
            Modifier
                .clickable(
                    enabled = enabled,
                    onClickLabel = onClickLabel,
                    onClick = { singleClickEventListener.processEvent { onClick() } },
                    role = role,
                    indication = currentIndication,
                    interactionSource = currentInteractionSource
                )
}
