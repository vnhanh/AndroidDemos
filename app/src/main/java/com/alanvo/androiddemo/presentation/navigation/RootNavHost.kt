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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.alanvo.androiddemo.presentation.MainViewModel
import com.alanvo.androiddemo.presentation.navigation.graphRoutes.Graph
import com.vnhanh.common.compose.toast.AppToast
import com.vnhanh.demo.feature.authentication.navigation.AuthenticationNavGraph
import com.vnhanh.demo.feature.authentication.navigation.authenticationGraph

@Composable
fun RootNavHost(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
) {
    val appToastState by mainViewModel.appToastState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .imePadding(),
    ) { paddingContent ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingContent),
        ) {

            /**
             * Ref best practice navigation with multi modules: https://www.youtube.com/watch?v=goFpG25uoc8
             */
            NavHost(
                navController = navHostController,
                route = Graph.Root.route,
                startDestination = AuthenticationNavGraph.GRAPH_ROUTE,
                enterTransition = { slideInHorizontally(tween(700)) { it } + fadeIn(tween(700)) },
                exitTransition = { slideOutHorizontally(tween(700)) { it } + fadeOut(tween(700)) },
            ) {
                authenticationGraph()
            }

            AppToast(
                modifier = Modifier.then(
                    if (appToastState?.positionType == AppToast.TOP) {
                        Modifier.align(Alignment.TopStart)
                    } else {
                        Modifier.align(Alignment.BottomStart)
                    }
                ),
                dataProvider = { appToastState },
                onClickCloseButton = { mainViewModel.hideBottomToast() }
            )
        }
    }
}
