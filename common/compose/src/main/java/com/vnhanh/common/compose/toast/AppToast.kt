package com.vnhanh.common.compose.toast

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vnhanh.common.compose.R
import com.vnhanh.common.compose.modifier.singleClick.singleClick
import com.vnhanh.common.compose.theme.AppTypography

object AppToast {
    const val TOP = 1
    const val BOTTOM = 2
}

data class AppToastUiModel(
    val title: String? = null,
    @ColorRes val titleColorRedId: Int = R.color.toast_title,
    val titleStyle: TextStyle = AppTypography.fontSize14LineHeight20SemiBold,
    val message: String? = null,
    val messageStyle: TextStyle = AppTypography.fontSize13LineHeight18Normal,
    @ColorRes val messageColorRedId: Int = R.color.toast_message,
    val leftIconData: AppToastIcon? = null,
    val rightIconData: AppToastIcon? = AppToastIcon(
        iconResId = R.drawable.ic_close_rounded_24_18,
        tintResId = R.color.toast_right_icon
    ),
    @ColorRes val backgroundColorResId: Int = R.color.toast_bg,
    val positionType: Int = AppToast.BOTTOM,
) {
    companion object {
        fun getDefault(
            title: String? = null,
            message: String? = null,
            positionType: Int = AppToast.BOTTOM
        ) =
            AppToastUiModel(
                title = title,
                message = message,
                positionType = positionType,
            )
    }
}

fun AppToastUiModel?.isNotBlank() =
    this != null && (title.isNullOrBlank().not() || message.isNullOrBlank().not())

data class AppToastIcon(
    @DrawableRes val iconResId: Int,
    val iconSizeDp: Int = 16,
    @ColorRes val tintResId: Int,
)

@Composable
fun BoxScope.AppToast(
    dataProvider: () -> AppToastUiModel?,
    modifier: Modifier = Modifier,
    onClickCloseButton: () -> Unit,
) {
    AnimatedVisibility(
        visible = dataProvider() != null && dataProvider().isNotBlank(),
        enter = (
                when (dataProvider()?.positionType) {
                    AppToast.TOP -> fadeIn(tween(300)) + slideInVertically(tween(300)) { -it }

                    else -> fadeIn(tween(300)) + slideInVertically(tween(300)) { it }
                }
                ),
        exit = (
                when (dataProvider()?.positionType) {
                    AppToast.TOP -> fadeOut(tween(300)) + slideOutVertically(tween(300)) { -it }

                    else -> fadeOut(tween(300)) + slideOutVertically(tween(300)) { it }
                }
                ),
        label = "show_hide_bottom_toast",
        modifier = modifier
            .then(
                when (dataProvider()?.positionType) {
                    AppToast.TOP -> {
                        Modifier.align(Alignment.TopStart)
                    }

                    else -> {
                        Modifier.align(Alignment.BottomStart)
                    }
                }
            )
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = if (dataProvider()?.positionType == AppToast.TOP) 16.dp else 0.dp,
                bottom = if (dataProvider()?.positionType == AppToast.BOTTOM) 24.dp else 0.dp
            ),
    ) {
        dataProvider()?.also { toastData ->
            Row(
                modifier = Modifier
                    .background(
                        color = colorResource(id = toastData.backgroundColorResId),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                toastData.leftIconData?.also { leftIconData ->
                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(CircleShape)
                            .singleClick {
                                onClickCloseButton()
                            }
                            .size(leftIconData.iconSizeDp.dp),
                        painter = painterResource(id = leftIconData.iconResId),
                        contentDescription = "close bottom toast button",
                        tint = colorResource(id = leftIconData.tintResId)
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                ) {
                    if (!toastData.title.isNullOrBlank()) {
                        println("TestAlan - toast title ${toastData.title}")
                        Text(
                            text = toastData.title,
                            style = toastData.messageStyle.copy(
                                textAlign = TextAlign.Start,
                            ),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }

                    toastData.message?.also { message ->
                        if (message.isNotBlank()) {
                            if (toastData.title.isNullOrBlank().not()) {
                                Spacer(Modifier.height(8.dp))
                            }
                            Text(
                                text = message,
                                style = toastData.messageStyle.copy(
                                    textAlign = TextAlign.Start,
                                ),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                    }
                }
                toastData.rightIconData?.also { rightIconData ->
                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(CircleShape)
                            .singleClick {
                                onClickCloseButton()
                            }
                            .size(rightIconData.iconSizeDp.dp),
                        painter = painterResource(id = rightIconData.iconResId),
                        contentDescription = "close bottom toast button",
                        tint = colorResource(id = rightIconData.tintResId)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AppToast(
            dataProvider = {
                AppToastUiModel(
                    title = "Title",
                    titleStyle = AppTypography.fontSize14LineHeight20SemiBold,
                    titleColorRedId = R.color.toast_title,
                    message = "Hello World!",
                    messageColorRedId = R.color.toast_message,
                    messageStyle = AppTypography.fontSize13LineHeight18Normal,
                    backgroundColorResId = R.color.toast_bg,
                )
            },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth(),
            onClickCloseButton = {},
        )
    }
}
