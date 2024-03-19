package com.vnhanh.demo.feature.authentication.presentation.forgotPassword.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.vnhanh.demo.feature.authentication.presentation.forgotPassword.ForgotPasswordScreen
import com.vnhanh.demo.feature.authentication.presentation.forgotPassword.navigation.ForgotPasswordNavigation.ROUTE


internal object ForgotPasswordNavigation {
    const val ROUTE = "forgot_password_screen"
}

fun NavGraphBuilder.forgotPasswordScreen(navHostController: NavHostController) {
    composable(
        route = ROUTE,
//        arguments = ForgotPasswordArgs.navArguments(),
    ) {
//        val savedStateHandle = backStackEntry.savedStateHandle
//        val args = ForgotPasswordArgs(savedStateHandle)

        ForgotPasswordScreen()
    }
}

// Public nav API
fun NavHostController.navigateToForgotPasswordScreen() {
    this.navigate(ROUTE)
}

//internal class ForgotPasswordArgs(val email: String) {
//    constructor(savedStateHandle: SavedStateHandle) : this(
//        savedStateHandle.get<String?>(ID_KEY).orEmpty()
//    )
//
//    companion object {
//        fun navArguments() = listOf(
//            navArgument(ID_KEY) {
//                type = NavType.StringType
//            }
//        )
//    }
//}
