package com.vnhanh.common.log

import timber.log.Timber

class AppDebugTree : Timber.DebugTree() {
    // Set format of timber tag as "fileName:lineNumber"
    override fun createStackElementTag(element: StackTraceElement): String = "${element.fileName}:${element.lineNumber}"
}
