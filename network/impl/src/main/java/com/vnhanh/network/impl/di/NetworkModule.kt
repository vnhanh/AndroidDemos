package com.vnhanh.network.impl.di

import com.vnhanh.demo.network.base.serviceCreator.IApiServiceCreator
import com.vnhanh.network.impl.httpClient.ApiServiceCreatorImpl
import com.vnhanh.network.impl.httpClient.AppOkhttpClient
import okhttp3.Interceptor
import org.koin.dsl.module

val networkModule = module {

    single { (curlTag: String, interceptors: List<Interceptor>, isDebugMode: Boolean) ->
        AppOkhttpClient.getBuilder(curlTag, interceptors, isDebugMode)
    }

    single<IApiServiceCreator> {
        ApiServiceCreatorImpl(get())
    }
}
