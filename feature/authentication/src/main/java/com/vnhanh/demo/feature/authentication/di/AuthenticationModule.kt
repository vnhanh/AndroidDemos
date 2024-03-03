package com.vnhanh.demo.feature.authentication.di

import com.vnhanh.demo.feature.authentication.common.AuthenticationConstants
import com.vnhanh.demo.feature.authentication.data.api.AuthenticationApi
import com.vnhanh.demo.feature.authentication.presentation.authentication.AuthenticationViewModel
import com.vnhanh.demo.feature.authentication.presentation.login.LoginViewModel
import com.vnhanh.demo.network.base.serviceCreator.IApiServiceCreator
import com.vnhanh.network.impl.httpClient.AppOkhttpClient
import okhttp3.Interceptor
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val authenticationModule = module {
    single(named(AuthenticationConstants.DI_SCOPE_NAME)) { (apiCreator: IApiServiceCreator, url: String) ->
        val testScope = getKoin().getScope("authentication").get<String>()
        println("TestAlan - testScope $testScope")
        apiCreator.createApiService(AuthenticationApi::class, url)
    }

    single(named(AuthenticationConstants.DI_SCOPE_NAME)) { (curlTag: String, interceptors: List<Interceptor>, isDebugMode: Boolean) ->
        AppOkhttpClient.getBuilder(curlTag, interceptors, isDebugMode)
    }

    viewModelOf(::AuthenticationViewModel)

    viewModelOf(::LoginViewModel)
}
