package com.vnhanh.common.compose.localCompositionals

import androidx.compose.ui.unit.Density


fun Int.asPxToDpValue(density: Density) = this / density.density
