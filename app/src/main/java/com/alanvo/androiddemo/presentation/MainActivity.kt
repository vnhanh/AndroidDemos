package com.alanvo.androiddemo.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.alanvo.androiddemo.presentation.navigation.RootNavHost
import com.vnhanh.common.compose.compositionLocalProviders.LocalAppWindowSize
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : FragmentActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    @OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            KoinAndroidContext {
                val windowSizeClass = calculateWindowSizeClass(activity = this@MainActivity)

                val navHostController = rememberNavController()

                CompositionLocalProvider(
                    LocalAppWindowSize provides windowSizeClass
                ) {
                    RootNavHost(
                        navHostController = navHostController,
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }

}
