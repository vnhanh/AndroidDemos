package com.alanvo.androiddemo.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.alanvo.androiddemo.presentation.MainViewModel
import com.alanvo.androiddemo.presentation.navigation.graphRoutes.Graph
import com.alanvo.androiddemo.presentation.navigation.navgiationGraphs.authentication.authenticationNavGraph
import com.vnhanh.common.compose.toast.AppToast

@Composable
fun RootNavHost(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
) {
    val appToastState = mainViewModel.appToastState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .imePadding(),
        bottomBar = {
            Text("Bottom Bar")
        },
    ) { paddingContent ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingContent),
        ) {
            NavHost(
                navController = navHostController,
                route = Graph.Root.route,
                startDestination = Graph.Authentication.route,
                enterTransition = { slideInHorizontally(tween(700)) { it } + fadeIn(tween(700)) },
                exitTransition = { slideOutHorizontally(tween(700)) { it } + fadeOut(tween(700)) },
            ) {
                authenticationNavGraph(navHostController)
            }

            AppToast(
                modifier = Modifier.align(Alignment.BottomStart),
                dataProvider = { appToastState.value },
                onClickCloseButton = { mainViewModel.hideBottomToast() }
            )
        }
    }
}
