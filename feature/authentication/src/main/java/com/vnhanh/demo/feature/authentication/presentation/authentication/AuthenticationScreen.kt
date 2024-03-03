package com.vnhanh.demo.feature.authentication.presentation.authentication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthenticationScreen(
    viewModel: AuthenticationViewModel = koinViewModel(),
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(modifier = Modifier.align(Alignment.Center), text = "Authentication")
    }
}
