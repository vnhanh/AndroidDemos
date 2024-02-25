package com.vnhanh.common.compose.dialog

import androidx.annotation.ColorRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vnhanh.common.compose.loading.LoadingComposable

@Composable
fun LoadingDialog(
    dialogVisibleProvider: () -> Boolean = { true },
    @ColorRes dimColorResId: Int,
    alphaDimFloat: Float = 0.65f,
    @ColorRes progressIndicatorColorResId: Int,
    progressIndicatorStrokeWidth: Dp = 4.dp,
    showingDialogContentAnimTime: Int = 300,
    hidingDialogContentAnimTime: Int = 200,
) {
    DialogAnimatedVisibility(
        dialogVisibleProvider = dialogVisibleProvider,
        isDismissOnBackPress = false,
        isDismissOnClickOutside = false,
        // no delay for showing loading composable content
        delayVisibleTime = 0,
        showingDialogContentAnimTime = showingDialogContentAnimTime,
        hidingDialogContentAnimTime = hidingDialogContentAnimTime,
        onDismissListener = null,
    ) {
        LoadingComposable(
            dimColorResId = dimColorResId,
            alphaDimFloat = alphaDimFloat,
            progressIndicatorColorResId = progressIndicatorColorResId,
            progressIndicatorStrokeWidth = progressIndicatorStrokeWidth
        )
    }
}
