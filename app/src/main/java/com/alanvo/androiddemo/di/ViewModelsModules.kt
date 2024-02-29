package com.alanvo.androiddemo.di

import com.alanvo.androiddemo.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        MainViewModel()
    }
}
