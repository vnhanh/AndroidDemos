package com.vnhanh.common.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AppTypography {
    val fontSize22LineHeight32SemiBold
        @Composable
        get() = TextStyle(
            fontSize = 22.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.SemiBold,
        )

    val fontSize14LineHeight20Normal
        @Composable
        get() = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.25.sp,
        )

    val fontSize14LineHeight20Medium
        @Composable
        get() = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.1.sp,
        )

    val fontSize11LineHeight13Normal
        @Composable
        get() = TextStyle(
            fontSize = 11.sp,
            lineHeight = 13.sp,
            fontWeight = FontWeight.Normal,
        )

    val fontSize11LineHeight20Normal
        @Composable
        get() = TextStyle(
            fontSize = 11.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Normal,
        )

    val fontSize12LineHeight16Normal
        get() = TextStyle(
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Normal,
        )

    val fontSize12LineHeight16SemiBold
        @Composable
        get() = TextStyle(
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.SemiBold,
        )

    val fontSize12LineHeight20Medium
        @Composable
        get() = TextStyle(
            fontSize = 12.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium,
        )

    val fontSize13LineHeight18Normal
        @Composable
        get() = TextStyle(
            fontSize = 13.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.Normal,
        )

    val fontSize13LineHeight18Medium
        @Composable
        get() = TextStyle(
            fontSize = 13.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.Medium,
        )

    val fontSize13LineHeight18SemiBold
        @Composable
        get() = TextStyle(
            fontSize = 13.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )

    val fontSize14LineHeight20SemiBold
        @Composable
        get() = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.SemiBold,
        )

    val fontSize15LineHeight20Medium
        @Composable
        get() = TextStyle(
            fontSize = 15.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium,
        )

    val fontSize16LineHeight22Bold
        @Composable
        get() = TextStyle(
            fontSize = 16.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.Bold,
        )
}
