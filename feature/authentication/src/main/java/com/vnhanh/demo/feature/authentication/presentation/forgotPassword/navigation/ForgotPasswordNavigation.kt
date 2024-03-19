package com.vnhanh.demo.feature.authentication.presentation.forgotPassword.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vnhanh.demo.feature.authentication.presentation.forgotPassword.ForgotPasswordScreen
import com.vnhanh.demo.feature.authentication.presentation.forgotPassword.navigation.ForgotPasswordNavigation.ID_KEY
import com.vnhanh.demo.feature.authentication.presentation.forgotPassword.navigation.ForgotPasswordNavigation.ROUTE


internal object ForgotPasswordNavigation {
    const val ROUTE = "forgot_password_screen"
    const val ID_KEY = "data_id"
}

fun NavGraphBuilder.forgotPasswordScreen() {
    composable(
        route = ROUTE,
        arguments = ForgotPasswordArgs.navArguments(),
    ) { backStackEntry ->
        val savedStateHandle = backStackEntry.savedStateHandle
        val args = ForgotPasswordArgs(savedStateHandle)

        ForgotPasswordScreen(args.email)
    }
}

// Public nav API
fun NavHostController.navigateToForgotPasswordScreen(dataId: String) {
    this.navigate("${ROUTE}/${dataId}")
}

internal class ForgotPasswordArgs(val email: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        savedStateHandle.get<String?>(ID_KEY).orEmpty()
    )

    companion object {
        fun navArguments() = listOf(
            navArgument(ID_KEY) {
                type = NavType.StringType
            }
        )
    }
}
