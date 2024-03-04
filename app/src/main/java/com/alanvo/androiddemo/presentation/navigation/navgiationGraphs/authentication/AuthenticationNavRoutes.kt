package com.alanvo.androiddemo.presentation.navigation.navgiationGraphs.authentication

sealed class AuthenticationNavRoutes(val route: String) {
    data object Authentication : AuthenticationNavRoutes("authentication")

    data object ForgotPassword : AuthenticationNavRoutes("forgot-password")
}
