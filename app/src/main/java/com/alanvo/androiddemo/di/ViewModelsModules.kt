package com.alanvo.androiddemo.di

import com.alanvo.androiddemo.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        MainViewModel(get(), get())
    }
}
