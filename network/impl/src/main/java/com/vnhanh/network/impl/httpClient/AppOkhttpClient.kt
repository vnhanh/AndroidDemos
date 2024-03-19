package com.vnhanh.network.impl.httpClient

import com.vnhanh.network.impl.BuildConfig
import com.vnhanh.network.impl.common.ApiConstant.CONNECTION_TIMEOUT
import com.vnhanh.network.impl.common.ApiConstant.OKHTTP_READ_TIMEOUT
import com.vnhanh.network.impl.common.ApiConstant.OKHTTP_WRITE_TIMEOUT
import com.vnhanh.network.impl.log.curl.CurlLoggerInterceptor
import com.vnhanh.network.impl.log.http.Logger
import com.vnhanh.network.impl.log.http.LoggingInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import java.util.concurrent.TimeUnit

object AppOkhttpClient {

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
            val curlLoggerInterceptor = CurlLoggerInterceptor(interceptorTag)

            // only add curl logger in debug mode
            clientBuilder
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
