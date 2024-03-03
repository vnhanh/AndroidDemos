package com.alanvo.androiddemo.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.alanvo.androiddemo.presentation.navigation.RootNavHost
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : FragmentActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            KoinAndroidContext {
                val navHostController = rememberNavController()

                RootNavHost(navHostController = navHostController, mainViewModel = mainViewModel)
            }
        }
    }

}
