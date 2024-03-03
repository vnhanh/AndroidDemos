package com.vnhanh.network.impl.di

import com.vnhanh.demo.network.base.serviceCreator.IApiServiceCreator
import com.vnhanh.network.impl.httpClient.ApiServiceCreatorImpl
import org.koin.dsl.module

val networkModule = module {

//    single(named(DI_SCOPE_NAME_NETWORK)) { (curlTag: String, interceptors: List<Interceptor>, isDebugMode: Boolean) ->
//        AppOkhttpClient.getBuilder(curlTag, interceptors, isDebugMode)
//    }

    single<IApiServiceCreator> {
        ApiServiceCreatorImpl(get())
    }
}
