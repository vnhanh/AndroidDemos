package com.alanvo.androiddemo.presentation.navigation.graphRoutes

sealed class Graph(val route: String) {
    data object Root : Graph("root")

    data object Authentication : Graph("authentication")
}
