package com.alanvo.androiddemo.presentation.navigation.navgiationGraphs.authentication

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.alanvo.androiddemo.presentation.navigation.graphRoutes.Graph
import com.vnhanh.demo.feature.authentication.presentation.authentication.AuthenticationScreen
import com.vnhanh.demo.feature.authentication.presentation.forgotPassword.ForgotPasswordScreen

fun NavGraphBuilder.authenticationNavGraph(
    navHostController: NavHostController,
) {
    navigation(
        route = Graph.Authentication.route,
        startDestination = AuthenticationNavRoutes.Authentication.route,
    ) {
        composable(
            route = AuthenticationNavRoutes.Authentication.route,
        ) {
            AuthenticationScreen()
        }
        composable(
            route = AuthenticationNavRoutes.Authentication.route,
        ) {
            ForgotPasswordScreen()
        }
    }
}
