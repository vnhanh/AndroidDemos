package com.vnhanh.common.datahelper.extensions

fun String?.ifNullOrBlank(defaultString: String) =
    if (!this.isNullOrBlank()) this else defaultString
