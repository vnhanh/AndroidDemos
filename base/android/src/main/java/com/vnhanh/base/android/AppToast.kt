package com.vnhanh.base.android

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

const val TOAST_POSITION_TOP = 1
const val TOAST_POSITION_BOTTOM = 2

@Parcelize
data class ToastUiModel(
    val title: String = "",
    val message: String? = null,
    val position: Int = TOAST_POSITION_BOTTOM,

    ) : Parcelable
