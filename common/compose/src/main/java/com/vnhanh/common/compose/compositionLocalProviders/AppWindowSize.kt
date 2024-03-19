package com.vnhanh.common.compose.compositionLocalProviders

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
val LocalAppWindowSize =
    staticCompositionLocalOf { WindowSizeClass.calculateFromSize(DpSize(400.dp, 800.dp)) }
