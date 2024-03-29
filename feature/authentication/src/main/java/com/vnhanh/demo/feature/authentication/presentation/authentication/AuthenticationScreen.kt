package com.vnhanh.demo.feature.authentication.presentation.authentication

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vnhanh.common.compose.modifier.fillMaxWidthLayoutResponsive
import com.vnhanh.common.compose.modifier.singleClick.singleClick
import com.vnhanh.demo.feature.authentication.R
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.LoginComposable
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login.SignInViewModel
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.register.RegisterComposable
import com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.register.SignUpViewModel


@Composable
internal fun AuthenticationScreen(
    authenticationViewModel: AuthenticationViewModel,
    loginViewModel: SignInViewModel,
    registerViewModel: SignUpViewModel,
    navHostController: NavHostController,
) {
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        loginViewModel.onStart()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .singleClick(isShowClickEffect = false) {
                focusManager.clearFocus()
            }
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Form(
            modifier = Modifier
                .fillMaxWidth(),
            loginViewModel = loginViewModel,
            registerViewModel = registerViewModel,
            navHostController = navHostController,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Form(
    modifier: Modifier = Modifier,
    loginViewModel: SignInViewModel,
    registerViewModel: SignUpViewModel,
    navHostController: NavHostController,
) {
    val pageState = rememberPagerState(pageCount = { 2 })

    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidthLayoutResponsive()
                .padding(horizontal = 24.dp)
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.authentication_secondary),
                    shape = RoundedCornerShape(24.dp)
                ),
            state = pageState,
            beyondBoundsPageCount = 1,
        ) { pageIndex: Int ->
            when (pageIndex) {
                0 -> {
                    LoginComposable(
                        modifier = Modifier.fillMaxWidth(),
                        signInViewModel = loginViewModel,
                        navHostController = navHostController,
                    )
                }

                1 -> {
                    RegisterComposable(
                        modifier = Modifier.fillMaxWidth(),
                        signUpViewModel = registerViewModel,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
