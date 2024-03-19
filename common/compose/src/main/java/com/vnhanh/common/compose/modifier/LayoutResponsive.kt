package com.vnhanh.common.compose.modifier

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.vnhanh.common.compose.compositionLocalProviders.LocalAppWindowSize


fun Modifier.fillMaxWidthLayoutResponsive() = composed {
    val windowSize = LocalAppWindowSize.current.heightSizeClass

    if (windowSize > WindowHeightSizeClass.Compact) {
        this.fillMaxWidth()
    } else {
        this.fillMaxWidth(0.7f)
    }
}
