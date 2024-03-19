package com.vnhanh.common.log

import timber.log.Timber

fun Exception.printDebugStackTrace() {
    Timber.e(this)
}

fun String.printBreakLineLog(tag: String, message: String) {
    Timber.tag(tag).d(message)
    var start = 0
    var end = minOf(this.length, 2000)
    while (end <= this.length) {
        Timber.tag(tag).d(this.substring(start, end))
        start = end
        end = minOf(this.length, start + 2000)
    }
}
