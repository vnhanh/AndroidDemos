package com.vnhanh.network.impl.log.http

import okhttp3.internal.platform.Platform


internal interface Logger {
    enum class Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    fun log(typeLog: Int, tag: String?, message: String?, useLogHack: Boolean)

    class DefaultLogger : Logger {
        override fun log(typeLog: Int, tag: String?, message: String?, useLogHack: Boolean) {
            val finalTag: String = getFinalTag(tag, useLogHack)
            val logger: java.util.logging.Logger = java.util.logging.Logger.getLogger(finalTag)

            when (typeLog) {
                Platform.INFO -> logger.log(java.util.logging.Level.INFO, message)
                else -> logger.log(java.util.logging.Level.WARNING, message)
            }
        }

        private fun getFinalTag(tag: String?, isLogHackEnable: Boolean): String {
            return if (isLogHackEnable) {
                index = index xor 1
                prefix[index] + tag
            } else {
                tag ?: defaultTag
            }
        }

        companion object {
            private const val defaultTag = "log-http-tag"
            private val prefix = arrayOf(". ", " .")
            private var index = 0

        }
    }

    companion object {
        val DEFAULT: Logger = DefaultLogger()
    }
}
