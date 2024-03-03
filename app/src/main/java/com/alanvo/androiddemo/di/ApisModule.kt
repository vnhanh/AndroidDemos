package com.alanvo.androiddemo.di

import com.alanvo.androiddemo.BuildConfig
import com.vnhanh.demo.feature.authentication.common.AuthenticationConstants
import com.vnhanh.network.impl.log.curl.CurlLoggerInterceptor
import com.vnhanh.network.impl.log.http.LoggingInterceptor
import okhttp3.Interceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module

val apisModule = module {
    single<List<Interceptor>> {
        listOf(
            CurlLoggerInterceptor("CURL"),
            LoggingInterceptor.create(isDebug = BuildConfig.DEBUG)
        )
    }

    // build type is debug or release
//    single(named("Build-Type")) { BuildConfig.BUILD_TYPE }

    // base url
//    single(named("Base-Url")) { BuildConfig.BASE_URL }

    // build type is debug or release
    single(named(AuthenticationConstants.DI_SCOPE_NAME)) { BuildConfig.BUILD_TYPE }

    // base url
    single(named(AuthenticationConstants.DI_SCOPE_NAME)) { BuildConfig.BASE_URL }

//    scope(named("Test")) {
//        scoped<String>(named("Build-Type")) { BuildConfig.BUILD_TYPE }
//        scoped<String>(named("Base-Url")) { BuildConfig.BUILD_TYPE }
//    }
}
