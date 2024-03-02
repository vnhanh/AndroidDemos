package com.alanvo.androiddemo.di

import com.vnhanh.network.impl.di.networkModule


val appModules = listOf(
    networkModule,
    viewModelsModule,
    useCasesModule,
    repositoriesModule,
    apisModule,
)
