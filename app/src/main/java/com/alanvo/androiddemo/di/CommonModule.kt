package com.alanvo.androiddemo.di

import androidx.lifecycle.SavedStateHandle
import com.google.gson.Gson
import org.koin.dsl.module

val commonModule = module {
    factory { Gson() }
    single { SavedStateHandle() }
}
