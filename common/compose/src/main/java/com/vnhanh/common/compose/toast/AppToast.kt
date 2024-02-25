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
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import com.vnhanh.common.compose.modifier.singleClick.singleClick

object AppToast {
    const val TOP = 1
    const val BOTTOM = 3
}

data class AppToastUiModel(
    val title: String = "",
    @ColorRes val titleColorRedId: Int,
    val titleStyle: TextStyle,
    val message: String? = null,
    val messageStyle: TextStyle,
    @ColorRes val messageColorRedId: Int,
    val leftIconData: AppToastIcon? = null,
    val rightIconData: AppToastIcon? = null,
    @ColorRes val backgroundColorResId: Int,
    val positionType: Int = AppToast.BOTTOM,
)

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
        visible = dataProvider() != null,
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
        modifier = Modifier
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
            .padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
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
                    Text(
                        text = toastData.title,
                        style = toastData.messageStyle.copy(
                            textAlign = TextAlign.Start,
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    toastData.message?.also { message ->
                        if (message.isNotBlank()) {
                            Spacer(Modifier.height(8.dp))
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
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}
