package com.vnhanh.common.compose.textfield

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun TrailIcon(
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int,
    tintColor: Color,
    contentDescription: String? = null,
    iconSize: Dp = 24.dp,
    paddingValues: PaddingValues = PaddingValues(4.dp),
) {
    Icon(
        modifier = modifier
            .padding(paddingValues)
            .size(iconSize),
        painter = painterResource(id = iconResId),
        tint = tintColor,
        contentDescription = contentDescription,
    )
}

@Composable
fun TrailIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    tintColor: Color,
    contentDescription: String? = null,
    iconSize: Dp = 24.dp,
    paddingValues: PaddingValues = PaddingValues(4.dp),
) {
    Icon(
        modifier = modifier
            .padding(paddingValues)
            .size(iconSize),
        painter = painter,
        tint = tintColor,
        contentDescription = contentDescription,
    )
}
