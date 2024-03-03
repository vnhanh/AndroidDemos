package com.alanvo.androiddemo.di

import com.vnhanh.demo.feature.authentication.di.authenticationModule
import com.vnhanh.network.impl.di.networkModule


val appModules = listOf(
    networkModule,
    viewModelsModule,
    useCasesModule,
    repositoriesModule,
    apisModule,
    authenticationModule,
)
