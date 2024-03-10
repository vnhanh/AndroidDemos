package com.vnhanh.demo.feature.authentication.presentation.authentication

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vnhanh.common.compose.modifier.fillMaxWidthLayoutResponsive
import com.vnhanh.demo.feature.authentication.presentation.login.LoginComposable
import com.vnhanh.demo.feature.authentication.presentation.login.LoginViewModel
import com.vnhanh.demo.feature.authentication.presentation.register.RegisterComposable
import com.vnhanh.demo.feature.authentication.presentation.register.RegisterViewModel


/**
 * TODO: make viewModel tight to lifecycle of screen
 */
@Composable
internal fun AuthenticationScreen(
    authenticationViewModel: AuthenticationViewModel,
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
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
            loginViewModel = loginViewModel,
            registerViewModel = registerViewModel,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Form(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
) {
    val pageState = rememberPagerState(pageCount = { 2 })
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        HorizontalPager(
            state = pageState,
            beyondBoundsPageCount = 1,
        ) { pageIndex: Int ->
            when (pageIndex) {
                0 -> {
                    LoginComposable(
                        modifier = Modifier.fillMaxWidthLayoutResponsive(),
                        loginViewModel = loginViewModel,
                    )
                }

                1 -> {
                    RegisterComposable(
                        modifier = Modifier.fillMaxWidthLayoutResponsive(),
                        registerViewModel = registerViewModel,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

    }
}
