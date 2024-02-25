package com.vnhanh.common.compose.modifier

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.IntSize

// TODO: must check why not use this before removing
fun Modifier.animateContentSizeAfterFirstShow(
    animationSpec: FiniteAnimationSpec<IntSize> = tween(200),
    finishedListener: ((initialValue: IntSize, targetValue: IntSize) -> Unit)? = null
) =
    Modifier.composed {
        var isLaunched by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            if (!isLaunched) isLaunched = true
        }

        if (isLaunched) {
            // only show animation change height of group sets after showing, or exactly removing set
            animateContentSize(animationSpec = tween(200))
        } else {
            // on launch this composable, not add animateContentSize() to not show animation
            Modifier
        }
    }
