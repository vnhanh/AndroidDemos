package com.vnhanh.common.datahelper.extensions

fun String?.ifNullOrBlank(block: () -> String) =
    if (!this.isNullOrBlank()) this else block()
