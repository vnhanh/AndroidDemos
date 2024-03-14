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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.vnhanh.common.compose.modifier.fillMaxWidthLayoutResponsive
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
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.2f)
        )
        Form(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f),
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
    loginViewModel: SignInViewModel,
    registerViewModel: SignUpViewModel,
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
