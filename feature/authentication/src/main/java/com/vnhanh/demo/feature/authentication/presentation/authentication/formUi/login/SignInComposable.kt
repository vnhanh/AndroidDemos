package com.vnhanh.demo.feature.authentication.presentation.authentication.formUi.login

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.vnhanh.common.compose.modifier.fillMaxWidthLayoutResponsive
import com.vnhanh.common.compose.modifier.singleClick.singleClick
import com.vnhanh.common.compose.theme.AppTypography.fontSize13LineHeight18Medium
import com.vnhanh.common.compose.theme.AppTypography.fontSize13LineHeight18SemiBold
import com.vnhanh.demo.feature.authentication.R
import com.vnhanh.demo.feature.authentication.presentation.components.AuthenticationButton
import com.vnhanh.demo.feature.authentication.presentation.components.EmailField
import com.vnhanh.demo.feature.authentication.presentation.components.PasswordField

/**
 * Enter email + password
 * Remember email + forgot password
 * Login by biometric
 * Save password after encode it
 * Allow login via Facebook, Google and Twitter account
 */
@Composable
internal fun LoginComposable(
    modifier: Modifier = Modifier,
    signInViewModel: SignInViewModel,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        EmailField(
            modifier = Modifier.fillMaxWidthLayoutResponsive(),
            fieldDataProvider = { signInViewModel.emailFieldData },
            onFieldValueChanged = { fieldValue -> signInViewModel.updateEmailField(fieldValue) }
        )
        Spacer(modifier = Modifier.height(24.dp))
        PasswordField(
            modifier = Modifier.fillMaxWidthLayoutResponsive(),
            fieldDataProvider = { signInViewModel.passwordFieldData },
            onFieldValueChanged = { fieldValue -> signInViewModel.updatePasswordField(fieldValue) },
        )
        Spacer(modifier = Modifier.height(8.dp))
        RememberAndForgotPassword(signInViewModel)
        Spacer(modifier = Modifier.height(36.dp))
        AuthenticationButton(
            buttonLabel = stringResource(id = R.string.sign_in),
            stateProvider = { signInViewModel.submitSignInState }
        ) {
            // onTap()
            signInViewModel.signIn()
        }
        Spacer(modifier = Modifier.height(16.dp))
        OrText()
        Spacer(modifier = Modifier.height(20.dp))
        SocialLoginButtons()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun RememberAndForgotPassword(
    viewModel: SignInViewModel,
) {

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        RememberMeCheckBox(rowScope = this, viewModel = viewModel)
        Spacer(modifier = Modifier.width(4.dp))
        ForgotPasswordButtonLink()
    }
}

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
private fun RememberMeCheckBox(rowScope: RowScope, viewModel: SignInViewModel) {
    var isRemember: Boolean by remember { mutableStateOf(false) }
    val animCheck: AnimatedImageVector =
        AnimatedImageVector.animatedVectorResource(id = R.drawable.anim_check)
    val checkAnimPainter = rememberAnimatedVectorPainter(
        animatedImageVector = animCheck,
        atEnd = isRemember
    )
    val colorAnimate: Color by animateColorAsState(
        targetValue = if (isRemember) Color.Black else colorResource(id = R.color.authentication_secondary),
        animationSpec = tween(400),
        label = "animate_change_checkbox_border_color"
    )

    with(rowScope) {
        Row(
            modifier = Modifier
                .weight(1f)
                .singleClick(isShowClickEffect = false) {
                    isRemember = !isRemember
                    viewModel.rememberEmail(isRemember = isRemember)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .padding(8.dp)
                    .border(
                        width = 1.dp,
                        color = colorAnimate,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                if (isRemember) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(20.dp),
                        painter = checkAnimPainter,
                        contentDescription = "Icon Checkbox remember this email"
                    )
                }
            }

            Text(
                text = stringResource(id = R.string.remember_me),
                style = fontSize13LineHeight18Medium.copy(
                    color = colorResource(id = R.color.authentication_secondary),
                    textAlign = TextAlign.Start,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun ForgotPasswordButtonLink() {
    Text(
        text = stringResource(id = R.string.forgot_password),
        style = fontSize13LineHeight18SemiBold.copy(
            color = colorResource(id = R.color.authentication_secondary),
            textAlign = TextAlign.Start,
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun OrText() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.or_uppercase),
        style = fontSize13LineHeight18SemiBold.copy(
            color = colorResource(id = R.color.authentication_secondary),
            textAlign = TextAlign.Center,
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun SocialLoginButtons() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Image(
            modifier = Modifier
                .singleClick {
                    // TODO
                }
                .clip(CircleShape)
                .padding(8.dp)
                .size(32.dp),
            painter = painterResource(id = R.drawable.ic_google_64),
            contentDescription = "Google Sign In Button"
        )
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            modifier = Modifier
                .singleClick {
                    // TODO
                }
                .clip(CircleShape)
                .padding(8.dp)
                .size(32.dp),
            painter = painterResource(id = R.drawable.ic_facebook_64),
            contentDescription = "Google Sign In Button"
        )
    }
}
