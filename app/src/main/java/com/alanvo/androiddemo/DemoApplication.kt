package com.alanvo.androiddemo

import android.app.Application
import com.vnhanh.common.log.AppDebugTree
import timber.log.Timber

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        if (BuildConfig.DEBUG) {
//            Timber.plant(AppDebugTree())
//        }
    }
}