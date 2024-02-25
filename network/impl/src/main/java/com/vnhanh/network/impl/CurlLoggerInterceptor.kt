package com.vnhanh.network.impl

import com.vnhanh.network.impl.responseLog.Printer.MAXIMUM_LENGTH_LOG_A_TAG
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import timber.log.Timber
import java.net.URLDecoder
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
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
            sb.append("cURL -g -X ")
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

                request.body?.also { requestBody ->
                    val buffer = Buffer()
                    requestBody.writeTo(buffer)

                    requestBody.contentType()?.also { contentType: MediaType ->
                        val utf8 = Charset.forName("UTF-8")
                        if (contentType.toString().startsWith("multipart/form-data")) {
                            addHeader(sb, "Content-Type", "multipart/form-data")

                            contentType.charset(utf8)?.also { charset: Charset ->
                                val temp = URLDecoder.decode(buffer.readString(charset), "UTF-8")
                                val lines = temp.split("\n")
                                lines.forEachIndexed { index, line: String ->
                                    if (line.contains("Content-Disposition: form-data")) {
                                        val index = line.indexOf("name")
                                        if (index > -1) {
                                            val name =
                                                line.substring(startIndex = index + 6, line.length)
                                                    .removeQuote()
                                            sb.append("--form '$name=\"")
                                        }
                                    } else {
                                        if (index > 0 && lines[index - 1].isBlank() && line.isNotBlank() && line.contains(
                                                "Content"
                                            ).not() && line.contains("--").not()
                                        ) {
                                            sb.append("$line\"' ")
                                        }
                                    }
                                }
                            }
                        } else {
                            addHeader(
                                sb,
                                "Content-Type",
                                request.body?.contentType()?.toString().orEmpty()
                            )

                            contentType.charset(utf8)?.also { charset: Charset ->
                                sb.append(" -d '")
                                    .append(URLDecoder.decode(buffer.readString(charset), "UTF-8"))
                                    .append("'")
                            }
                        }
                    }
                }
            }

            // add request URL
            sb.append(" \'").append(request.url.toString()).append("\'")
            sb.append(" -L")

            print(tag, sb.toString())
        } catch (e: Exception) {
            Timber.e("Can not log Curl, error: %s", e.message.orEmpty())
        }

        return chain.proceed(request)
    }

    private fun String.removeQuote() = this.replace("\"", "")

    private fun addHeader(
        sb: StringBuffer,
        headerName: String,
        headerValue: String?
    ) {
        sb.append("-H " + "\'")
            .append(headerName)
            .append(": ")
            .append(headerValue)
            .append("\' ")
    }

    private fun print(tag: String?, msg: String) {
        // setting tag if not null and not blank
        if (!tag.isNullOrEmpty()) curlTag = tag

        val logMsg = StringBuilder("\n")
        logMsg.append("\n")
        logMsg.append(singleDivider)
        logMsg.append("\n")

        printLogLine(logMsg, msg)

        logMsg.append(" ")
        logMsg.append(" \n")
        logMsg.append(singleDivider)
        logMsg.append(" \n ")
        Timber.tag(curlTag).d(logMsg.toString())
    }


    // Divider of curl log
    private val singleDivider = "────────────────────────────────────────────"

    private fun printLogLine(logMsg: StringBuilder, msg: String) {
        val messageLength = msg.length
        val divide = messageLength / MAXIMUM_LENGTH_LOG_A_TAG
        val linesNumber = if (divide * MAXIMUM_LENGTH_LOG_A_TAG < messageLength) {
            divide + 1
        } else {
            divide
        }

        repeat(linesNumber) { lineIndex ->
            val start = lineIndex * messageLength
            var end = (lineIndex + 1) * messageLength
            end = if (end > messageLength) messageLength else end
            logMsg.append(
                URLDecoder.decode(
                    msg.substring(start, end),
                    StandardCharsets.UTF_8.toString()
                )
            )
        }
    }
}
