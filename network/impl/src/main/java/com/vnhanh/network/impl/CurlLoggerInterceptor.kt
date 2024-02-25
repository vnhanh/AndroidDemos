package com.vnhanh.network.impl

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import timber.log.Timber
import java.net.URLDecoder
import java.nio.charset.Charset
import java.util.Locale

/**
 * Curl log printer
 * Added to OkHttpClient objects
 */
class CurlLoggerInterceptor(private val tag: String) : Interceptor {
    private var curlTag = "CURL"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        try {
            val sb = StringBuffer("")
            // add cURL command
            sb.append("cURL -g ")
            sb.append("-X ")
            // add method
            sb.append(request.method.uppercase(Locale.ROOT)).append(" ")
            // adding headers
            for (headerName in request.headers.names()) {
                addHeader(sb, headerName, request.headers[headerName])
            }

            // adding request body
            request.body?.also { requestBody ->
                val buffer = Buffer()
                requestBody.writeTo(buffer)

                requestBody.contentType()?.also { contentType ->
                    addHeader(sb, "Content-Type", contentType.toString())

                    val utf8 = Charset.forName("UTF-8")
                    contentType.charset(utf8)?.also { charset ->
                        sb.append(" -d '")
                            .append(URLDecoder.decode(buffer.readString(charset), "UTF-8"))
                            .append("'")
                    }
                }
            }

            // add request URL
            sb.append(" \"").append(request.url.toString()).append("\"")
            sb.append(" -L")

            print(tag, sb.toString())
        } catch (e: Exception) {
            Timber.e("Can not log Curl, error: %s", e.message.orEmpty())
        }

        return chain.proceed(request)
    }

    private fun addHeader(
        sb: StringBuffer,
        headerName: String,
        headerValue: String?
    ) {
        sb.append("-H " + "\"")
            .append(headerName)
            .append(": ")
            .append(headerValue)
            .append("\" ")
    }

    private fun print(tag: String?, msg: String) {
        // setting tag if not null and not blank
        if (!tag.isNullOrEmpty()) curlTag = tag

        // Divider of curl log
        val singleDivider = "────────────────────────────────────────────"

        val logMsg = StringBuilder("\n")
        logMsg.append("\n")
        logMsg.append(singleDivider)
        logMsg.append("\n")
        logMsg.append(msg)
        logMsg.append(" ")
        logMsg.append(" \n")
        logMsg.append(singleDivider)
        logMsg.append(" \n ")
        Timber.tag(curlTag).d(logMsg.toString())
    }
}
