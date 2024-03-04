package com.vnhanh.demo.feature.authentication.presentation.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vnhanh.common.compose.modifier.fillMaxWidthLayoutResponsive
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthenticationScreen(
    viewModel: AuthenticationViewModel = koinViewModel(),
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        )
        Form(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .verticalScroll(rememberScrollState()),
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Composable
private fun Form(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        LoginAndSignupButtons(modifier = Modifier.fillMaxWidthLayoutResponsive())
        Spacer(modifier = Modifier.height(24.dp))

    }
}

@Composable
private fun LoginAndSignupButtons(
    modifier: Modifier = Modifier,
) {
    Row(modifier.padding(horizontal = 16.dp)) {

    }
}
