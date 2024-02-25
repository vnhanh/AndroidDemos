package com.vnhanh.network.impl

import com.vnhanh.network.impl.ApiConstant.CONNECTION_TIMEOUT
import com.vnhanh.network.impl.ApiConstant.OKHTTP_READ_TIMEOUT
import com.vnhanh.network.impl.ApiConstant.OKHTTP_WRITE_TIMEOUT
import com.vnhanh.network.impl.responseLog.Logger
import com.vnhanh.network.impl.responseLog.LoggingInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkhttpClient {

    fun getBuilder(
        interceptorTag: String,
        interceptors: List<Interceptor> = emptyList(),
        isDebugMode: Boolean,
    ): OkHttpClient {
        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OKHTTP_READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(OKHTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)

        interceptors.forEach { interceptor ->
            clientBuilder.addInterceptor(interceptor)
        }

        if (isDebugMode) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            val curlLoggerInterceptor = CurlLoggerInterceptor(interceptorTag)

            // only add curl logger in debug mode
            clientBuilder
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(curlLoggerInterceptor)
                .addInterceptor(getLoggingInterceptor())
        }

        return clientBuilder.build()
    }

    fun getLoggingInterceptor(): LoggingInterceptor =
        LoggingInterceptor.Builder()
            .loggable(BuildConfig.DEBUG)
            .setLevel(Logger.Level.BASIC)
            .log(Platform.INFO)
            .request("App-Request")
            .response("App-Response")
            .addHeader("Content-Type", "application/json")
            .enablelogModeForCopy(true)
            .build()
}
