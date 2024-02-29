package com.alanvo.androiddemo

import android.app.Application
import com.alanvo.androiddemo.di.apisModule
import com.alanvo.androiddemo.di.repositoriesModule
import com.alanvo.androiddemo.di.useCasesModule
import com.alanvo.androiddemo.di.viewModelsModule
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
            androidLogger(Level.ERROR)
            androidContext(this@DemoApplication)
            // TODO: should study it later
            androidFileProperties()
            viewModelsModule
            useCasesModule
            repositoriesModule
            apisModule
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(AppDebugTree())
        }
    }
}
