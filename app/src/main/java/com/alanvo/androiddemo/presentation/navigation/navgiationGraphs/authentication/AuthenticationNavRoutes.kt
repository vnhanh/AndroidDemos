package com.alanvo.androiddemo.presentation.navigation.navgiationGraphs.authentication

sealed class AuthenticationNavRoutes(val route: String) {
    data object Login : AuthenticationNavRoutes("login")

    data object Register : AuthenticationNavRoutes("register")
}
