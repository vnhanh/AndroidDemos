package com.alanvo.androiddemo

import android.app.Application
import com.alanvo.androiddemo.di.appModules
import com.vnhanh.common.log.AppDebugTree
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DemoApplication)
            androidLogger(Level.ERROR)
            // TODO: should study it later
            androidFileProperties()
            modules(appModules)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(AppDebugTree())
        }
    }
}
