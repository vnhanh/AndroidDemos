package com.vnhanh.demo.feature.authentication.presentation.authentication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.vnhanh.demo.feature.authentication.presentation.authentication.AuthenticationScreen
import com.vnhanh.demo.feature.authentication.presentation.authentication.AuthenticationViewModel
import com.vnhanh.demo.feature.authentication.presentation.login.LoginViewModel
import com.vnhanh.demo.feature.authentication.presentation.register.RegisterViewModel
import org.koin.androidx.compose.koinViewModel


internal object AuthenticationScreenNav {
    const val DESTINATION = "authentication_screen"
}

fun NavGraphBuilder.authenticationScreen() {
    composable(
        route = AuthenticationScreenNav.DESTINATION,
    ) {
        val viewModel = koinViewModel<AuthenticationViewModel>()
        val loginViewModel = koinViewModel<LoginViewModel>()
        val registerViewModel = koinViewModel<RegisterViewModel>()

        AuthenticationScreen(
            authenticationViewModel = viewModel,
            loginViewModel = loginViewModel,
            registerViewModel = registerViewModel
        )
    }
}

// Public nav API
fun NavHostController.authenticationScreen() {
    this.navigate(AuthenticationScreenNav.DESTINATION)
}
