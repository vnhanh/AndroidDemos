package com.alanvo.androiddemo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.vnhanh.common.compose.toast.AppToast
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
                val appToastState = mainViewModel.appToastState.collectAsStateWithLifecycle()

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppToast(
                        dataProvider = { appToastState.value },
                        onClickCloseButton = { mainViewModel.hideBottomToast() }
                    )
                }
            }
        }
    }

}
