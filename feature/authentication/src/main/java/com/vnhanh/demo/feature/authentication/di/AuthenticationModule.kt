package com.vnhanh.demo.feature.authentication.di

import com.google.gson.Gson
import com.vnhanh.demo.feature.authentication.data.api.AuthenticationApi
import com.vnhanh.demo.feature.authentication.domain.validation.AuthenticationFieldValidationUseCase
import com.vnhanh.demo.feature.authentication.presentation.authentication.AuthenticationViewModel
import com.vnhanh.demo.feature.authentication.presentation.login.LoginViewModel
import com.vnhanh.demo.feature.authentication.presentation.register.RegisterViewModel
import com.vnhanh.demo.network.base.serviceCreator.IApiServiceCreator
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val authenticationModule = module {
    factory { Gson() }

    /**
     * Api + Network
     */
    single { (apiCreator: IApiServiceCreator, url: String) ->
        val testScope = getKoin().getScope("authentication").get<String>()
        println("TestAlan - testScope $testScope")
        apiCreator.createApiService(AuthenticationApi::class, url)
    }

//    single(named(AuthenticationConstants.DI_SCOPE_NAME)) { (curlTag: String, interceptors: List<Interceptor>, isDebugMode: Boolean) ->
//        AppOkhttpClient.getBuilder(curlTag, interceptors, isDebugMode)
//    }

    /**
     * UseCases
     */
    factory {
        AuthenticationFieldValidationUseCase(get())
    }

    /**
     * ViewModels
     */
    viewModelOf(::AuthenticationViewModel)

    viewModelOf(::LoginViewModel)

    viewModelOf(::RegisterViewModel)
}

private val loadAuthKoinModules by lazy { loadKoinModules(authenticationModule) }

fun injectAuthKoinModule() = loadAuthKoinModules
