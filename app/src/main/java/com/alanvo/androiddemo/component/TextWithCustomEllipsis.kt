package com.alanvo.androiddemo.component

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

enum class AppEllipsisPosition {
    START,
    END
}

/**
 * Caution: only use if the default composable can not show ok
 */
@Composable
fun AppTextCustomEllipse(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    softWrap: Boolean = true,
    ellipsisAt: AppEllipsisPosition = AppEllipsisPosition.END,
    maxLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
) {
    val ellipsisCharsCount = 3
    val ellipsisChar = '.'
    val ellipsisText: String = List(ellipsisCharsCount) { ellipsisChar }.joinToString(separator = "")

    // some letters, like "r", will have less width when placed right before "."
    // adding a space to prevent such case
    val layoutText = remember(text) { "$text $ellipsisText" }
    val textLayoutResultState = remember(layoutText) {
        mutableStateOf<TextLayoutResult?>(null)
    }
    SubcomposeLayout(modifier) { constraints ->
        // result is ignored - we only need to fill our textLayoutResult
        subcompose("measure") {
            Text(
                text = layoutText,
                color = color,
                fontSize = fontSize,
                fontStyle = fontStyle,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                letterSpacing = letterSpacing,
                textDecoration = textDecoration,
                textAlign = textAlign,
                lineHeight = lineHeight,
                softWrap = softWrap,
                maxLines = 1,
                onTextLayout = { textLayoutResultState.value = it },
                style = style,
            )
        }.first().measure(Constraints())

        val textLayoutResult: TextLayoutResult = textLayoutResultState.value
            ?: // shouldn't happen - onTextLayout is called before subcompose finishes
            return@SubcomposeLayout layout(0, 0) {}
        val placeable: Placeable = subcompose("visible") {
            val finalText: String = remember(text, textLayoutResult, constraints.maxWidth) {
                if (text.isEmpty() || textLayoutResult.getBoundingBox(text.indices.last).right <= constraints.maxWidth) {
                    // text not including ellipsis fits on the first line.
                    return@remember text
                }

                val ellipsisWidth: Float = layoutText.indices.toList()
                    .takeLast(ellipsisCharsCount)
                    .let widthLet@{ indices ->
                        // fix this bug: https://issuetracker.google.com/issues/197146630
                        // in this case width is invalid
                        for (i: Int in indices) {
                            val width: Float = textLayoutResult.getBoundingBox(i).width
                            if (width > 0) {
                                return@widthLet width * ellipsisCharsCount
                            }
                        }
                        // this should not happen, because
                        // this error occurs only for the last character in the string
                        return@widthLet 0f
                    }

                when (ellipsisAt) {
                    AppEllipsisPosition.START -> {
                        calculateTextForEllipsisAtStart(
                            text = text,
                            eachLineWidth = constraints.maxWidth * 1f,
                            ellipsisCharsWidth = ellipsisWidth,
                            ellipsisText = ellipsisText,
                            maxLines = maxLines,
                            textLayoutResult = textLayoutResult
                        )
                    }

                    AppEllipsisPosition.END -> {
                        calculateTextForEllipsisAtEnd(
                            text = text,
                            eachLineWidth = constraints.maxWidth * 1f,
                            ellipsisCharsWidth = ellipsisWidth,
                            ellipsisText = ellipsisText,
                            maxLines = maxLines,
                            textLayoutResult = textLayoutResult
                        )
                    }
                }
            }

            Text(
                text = finalText,
                color = color,
                fontSize = fontSize,
                fontStyle = fontStyle,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                letterSpacing = letterSpacing,
                textDecoration = textDecoration,
                textAlign = textAlign,
                lineHeight = lineHeight,
                softWrap = softWrap,
                onTextLayout = onTextLayout,
                style = style,
            )
        }[0].measure(constraints)

        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
}

private fun calculateTextForEllipsisAtStart(
    text: String,
    eachLineWidth: Float,
    ellipsisCharsWidth: Float,
    ellipsisText: String,
    maxLines: Int,
    textLayoutResult: TextLayoutResult,
): String {
    val boundCounter = BoundCounter(
        text = text,
        maxLines = maxLines,
        lineWidth = eachLineWidth,
        ellipsisCharsWidth = ellipsisCharsWidth,
        ellipsisAtLine = 1,
        textLayoutResult = textLayoutResult
    ) { text.indices.last - it }
    while (boundCounter.isNotReachEndText() && boundCounter.canAddMoreChar()) {
        if (boundCounter.canAddNextChar()) {
            boundCounter.addNextChar()
        } else {
            break
        }
    }

    return if (boundCounter.isReachEndText()) {
        text
    } else {
        ellipsisText + boundCounter.string.reversed()
    }
}

private fun calculateTextForEllipsisAtEnd(
    text: String,
    eachLineWidth: Float,
    ellipsisCharsWidth: Float,
    ellipsisText: String,
    maxLines: Int,
    textLayoutResult: TextLayoutResult,
): String {
    val boundCounter = BoundCounter(
        text = text,
        maxLines = maxLines,
        lineWidth = eachLineWidth,
        ellipsisCharsWidth = ellipsisCharsWidth,
        ellipsisAtLine = maxLines,
        textLayoutResult = textLayoutResult
    ) { it }

    while (boundCounter.isNotReachEndText() && boundCounter.canAddMoreChar()) {
        if (boundCounter.canAddNextChar()) {
            boundCounter.addNextChar()
        } else {
            break
        }
    }

    return if (boundCounter.isReachEndText()) {
        text
    } else {
        boundCounter.string + ellipsisText
    }
}

private class BoundCounter(
    private val text: String,
    private val maxLines: Int = 1,
    private val lineWidth: Float,
    private val ellipsisCharsWidth: Float,
    private val textLayoutResult: TextLayoutResult,
    private val ellipsisAtLine: Int = 1,
    private val charPosition: (Int) -> Int,
) {
    var string = ""
        private set

    var currentWordWidth = 0f
        private set

    private var _nextCharWidth: Float? = null
    private var invalidCharsCount = 0
    private var lineIndex = 0
    private var currentLineWidth = 0f
    private var currentLineAvailableWidth = 0f
    // support case long word occupies 2 or more lines
    // in this case only calculate word width for 1 time next line
    private var availableCalculatWidthInCaseNextLine = true

    init {
        updateOnNewLine()
    }

    fun nextChar() : Char = text[charPosition(string.count())]

    fun isReachEndText() : Boolean = string.count() + invalidCharsCount >= text.length

    fun isNotReachEndText() : Boolean = !isReachEndText()

    fun canAddMoreChar() : Boolean = currentLineWidth < currentLineAvailableWidth

    fun canAddNextChar(): Boolean = lineIndex < maxLines || widthWithNextChar() <= currentLineAvailableWidth

    fun nextCharWidth(): Float =
        _nextCharWidth ?: run {
            var boundingBox: Rect
            // invalidCharsCount fixes this bug: https://issuetracker.google.com/issues/197146630
            invalidCharsCount--
            do {
                val index = string.count() + ++invalidCharsCount
                if (index < 0 || index >= text.length) {
                    return@run 0f
                }
                boundingBox = textLayoutResult
                    .getBoundingBox(charPosition(index))
            } while (boundingBox.right == 0f)
            _nextCharWidth = boundingBox.width
            boundingBox.width
        }

    fun widthWithNextChar(): Float = currentLineWidth + nextCharWidth()

    fun addNextChar() {
        if (isReachEndText()) return
        val nextChar: Char = nextChar()
        val nextCharWidth: Float = nextCharWidth()
        val isGoToNextLine: Boolean = currentLineWidth + nextCharWidth > currentLineAvailableWidth
        val widthWillBeAdded: Float = calculateWidthWillBeAdded(
            nextChar = nextChar,
            nextCharWidth = nextCharWidth,
            isGoToNextLine = isGoToNextLine,
        )

        if (isGoToNextLine) {
            updateOnNewLine()
        }
        currentLineWidth += widthWillBeAdded
        string += nextChar

        _nextCharWidth = null
    }

    private fun calculateWidthWillBeAdded(
        nextChar: Char,
        nextCharWidth: Float,
        isGoToNextLine: Boolean
    ) : Float {
        val widthWillBeAdded: Float

        if (nextChar.isWhitespace()) {
            widthWillBeAdded = if (isGoToNextLine) {
                0f
            } else {
                nextCharWidth
            }
            currentWordWidth = 0f
            availableCalculatWidthInCaseNextLine = true
        } else {
            if (isGoToNextLine && availableCalculatWidthInCaseNextLine) {
                availableCalculatWidthInCaseNextLine = false
                widthWillBeAdded = currentWordWidth + nextCharWidth
                currentWordWidth = 0f
            } else {
                widthWillBeAdded = nextCharWidth
            }
            currentWordWidth += widthWillBeAdded
        }

        return widthWillBeAdded
    }

    private fun updateOnNewLine() {
        lineIndex++
        currentLineWidth = 0f
        currentLineAvailableWidth = if (lineIndex == ellipsisAtLine) {
            lineWidth - ellipsisCharsWidth
        } else {
            lineWidth
        }
    }
}


@Preview
@Composable
private fun LGPreviewTextCustomEllipsis(
    @PreviewParameter(LGPreviewProviderParamTextCustomEllipsis::class) input: LGPreviewDataTextCustomEllipsis
) {
    AppTextCustomEllipse(
        modifier = Modifier
            .width(300.dp)
            .wrapContentHeight(),
        text = input.text,
        ellipsisAt = input.ellipsisAt,
        maxLines = input.maxLines,
    )
}

data class LGPreviewDataTextCustomEllipsis(
    val text: String,
    val maxLines: Int,
    val ellipsisAt: AppEllipsisPosition,
)

class LGPreviewProviderParamTextCustomEllipsis : PreviewParameterProvider<LGPreviewDataTextCustomEllipsis> {
    override val values: Sequence<LGPreviewDataTextCustomEllipsis> = sequenceOf(
        LGPreviewDataTextCustomEllipsis(
            text = "Bộ trưởng Nông nghiệp & Phát triển nông thôn ước tính ngoài lượng tiêu thụ nội địa, cả nước còn khoảng 13,5 triệu tấn thóc, tương đương 7-8 triệu tấn gạo cho xuất khẩu. Số liệu này được Bộ trưởng Nông nghiệp & Phát triển nông thôn Lê Minh Hoan nêu trong báo cáo giải trình các vấn đề chất vấn gửi các đại biểu Quốc hội ngày 10/8. Theo kế hoạch, Bộ trưởng Lê Minh Hoan sẽ đăng đàn trả lời chất vấn các vấn đề của ngành nông nghiệp tại phiên họp thứ 25 Ủy ban Thường vụ Quốc hội, chiều 15/8.",
            maxLines = 1,
            ellipsisAt = AppEllipsisPosition.START
        ),
        LGPreviewDataTextCustomEllipsis(
            text = "Bộ trưởng Nông nghiệp & Phát triển nông thôn ước tính ngoài lượng tiêu thụ nội địa, cả nước còn khoảng 13,5 triệu tấn thóc, tương đương 7-8 triệu tấn gạo cho xuất khẩu. Số liệu này được Bộ trưởng Nông nghiệp & Phát triển nông thôn Lê Minh Hoan nêu trong báo cáo giải trình các vấn đề chất vấn gửi các đại biểu Quốc hội ngày 10/8. Theo kế hoạch, Bộ trưởng Lê Minh Hoan sẽ đăng đàn trả lời chất vấn các vấn đề của ngành nông nghiệp tại phiên họp thứ 25 Ủy ban Thường vụ Quốc hội, chiều 15/8.",
            maxLines = 1,
            ellipsisAt = AppEllipsisPosition.END
        ),
    )
}
