package com.vnhanh.common.log

import timber.log.Timber

fun Exception.printDebugStackTrace() {
    Timber.e(this)
}
