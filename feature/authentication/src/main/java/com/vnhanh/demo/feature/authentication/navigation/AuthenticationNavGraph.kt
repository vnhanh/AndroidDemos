package com.vnhanh.demo.feature.authentication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.vnhanh.demo.feature.authentication.navigation.AuthenticationNavGraph.GRAPH_ROUTE
import com.vnhanh.demo.feature.authentication.presentation.authentication.navigation.AuthenticationScreenNav
import com.vnhanh.demo.feature.authentication.presentation.authentication.navigation.authenticationScreen
import com.vnhanh.demo.feature.authentication.presentation.forgotPassword.navigation.forgotPasswordScreen

object AuthenticationNavGraph {
    const val GRAPH_ROUTE = "authentication_graph"
}

fun NavGraphBuilder.authenticationGraph(navHostController: NavHostController) {
    navigation(
        route = GRAPH_ROUTE,
        startDestination = AuthenticationScreenNav.DESTINATION,
    ) {
        authenticationScreen(navHostController)
        forgotPasswordScreen(navHostController)
    }
}
