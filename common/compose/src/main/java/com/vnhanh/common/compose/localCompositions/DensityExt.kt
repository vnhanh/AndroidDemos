package com.vnhanh.common.compose.localCompositions

import androidx.compose.ui.unit.Density


fun Int.asPxToDpValue(density: Density) = this / density.density
