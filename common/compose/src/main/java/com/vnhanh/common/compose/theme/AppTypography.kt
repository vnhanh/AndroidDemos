package com.vnhanh.common.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

object AppTypography {
    val m3HeadlineSmallSemiBold
        @Composable
        get() = TextStyle(
            fontSize = 22.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.SemiBold,
        )

    val m3BodyMedium
        @Composable
        get() = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.25.sp,
        )

    val m3LabelLarge
        @Composable
        get() = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.1.sp,
        )

    val headerTitle
        @Composable
        get() = TextStyle(
            fontSize = 15.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
        )

    val headerButton
        @Composable
        get() = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium,
        )

    val fontSize11LineHeight13FontWeight400
        @Composable
        get() = TextStyle(
            fontSize = 11.sp,
            lineHeight = 13.sp,
            fontWeight = FontWeight.Normal,
        )

    val fontSize11LineHeight20FontWeight400
        @Composable
        get() = TextStyle(
            fontSize = 11.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Normal,
        )

    val fontSize12LineHeight16FontWeight400
        @Composable
        get() = TextStyle(
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Normal,
        )

    val fontSize12LineHeight16FontWeight600
        @Composable
        get() = TextStyle(
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.SemiBold,
        )

    val fontSize12LineHeight20FontWeight500
        @Composable
        get() = TextStyle(
            fontSize = 12.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium,
        )

    val fontSize13LineHeight18FontWeight400
        @Composable
        get() = TextStyle(
            fontSize = 13.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.Normal,
        )

    val fontSize13LineHeight18FontWeight500
        @Composable
        get() = TextStyle(
            fontSize = 13.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.Medium,
        )

    val fontSize13LineHeight18FontWeight600
        @Composable
        get() = TextStyle(
            fontSize = 13.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )

    val fontSize14LineHeight20FontWeight600
        @Composable
        get() = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.SemiBold,
        )

    val fontSize15LineHeight20FontWeight500
        @Composable
        get() = TextStyle(
            fontSize = 15.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium,
        )
}
