package com.vnhanh.common.compose.context

import android.content.Context
import androidx.compose.ui.graphics.Color


fun Context.getColorResource(id: Int) = Color(this.resources.getColor(id, this.theme))
