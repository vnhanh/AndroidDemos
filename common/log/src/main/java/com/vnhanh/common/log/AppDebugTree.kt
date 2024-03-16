package com.vnhanh.common.log

import timber.log.Timber

class AppDebugTree : Timber.DebugTree() {
    // Set format of timber tag as "fileName:lineNumber"
    override fun createStackElementTag(element: StackTraceElement): String =
        "${element.fileName}:${element.lineNumber}"
}

object AppLog {

    fun plantLogTree() {
        if (BuildConfig.DEBUG) {
            Timber.plant(AppDebugTree())
        }
    }

    fun tag(tag: String = "") = Tag(tag)

    fun d(message: String?, vararg args: Any?) = tag().d(message, args)

    fun e(message: String?, vararg args: Any?) = tag().e(message, args)

    class Tag(private val tag: String = "") {
        fun d(message: String?, vararg args: Any?) {
            if (tag.isBlank()) {
                Timber.d(message, args)
            } else {
                Timber.tag(tag).d(message, args)
            }
        }

        fun e(message: String?, vararg args: Any?) {
            if (tag.isBlank()) {
                Timber.e(message, args)
            } else {
                Timber.tag(tag).e(message, args)
            }
        }
    }
}
