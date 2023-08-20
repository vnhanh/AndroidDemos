package com.alanvo.androiddemo.component

import android.graphics.CornerPathEffect
import android.graphics.DiscretePathEffect
import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toComposePathEffect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

fun DrawScope.drawQrBorderCanvas(
    borderColor: Color = Color.White,
    curve: Dp,
    strokeWidth: Dp,
    capSize: Dp,
    gapAngle: Int = 20,
    shadowSize: Dp = strokeWidth * 2,
    cap: StrokeCap = StrokeCap.Square,
    lineCap: StrokeCap = StrokeCap.Round,
) {
    val curvePx = curve.toPx()
    val mCapSize = capSize.toPx()

    val width = size.width
    val height = size.height

    val sweepAngle = 90 / 2 - gapAngle / 2f

//    strokeWidth.toPx().toInt()
//    for (i in 4..shadowSize.toPx().toInt() step 2) {
//        drawRoundRect(
//            color = Color(0x05000000),
//            size = size,
//            topLeft = Offset(0f, 0f),
//            style = Stroke(width = i * 1f),
//            cornerRadius = CornerRadius(
//                x = curvePx,
//                y = curvePx
//            ),
//        )
//    }

    val mCurve: Float = curvePx * 2

    drawArc(
        color = borderColor,
        style = Stroke(strokeWidth.toPx(), cap = cap),
        startAngle = 0f,
        sweepAngle = sweepAngle,
        useCenter = false,
        size = Size(mCurve , mCurve ),
        topLeft = Offset(
            width - mCurve , height - mCurve
        )
    )
    drawArc(
        color = borderColor,
        style = Stroke(strokeWidth.toPx(), cap = cap),
        startAngle = 90 - sweepAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        size = Size(mCurve , mCurve ),
        topLeft = Offset(
            width - mCurve , height - mCurve
        )
    )

    drawArc(
        color = borderColor,
        style = Stroke(strokeWidth.toPx(), cap = cap),
        startAngle = 90f,
        sweepAngle = sweepAngle,
        useCenter = false,
        size = Size(mCurve, mCurve),
        topLeft = Offset(
            0f, height - mCurve
        )
    )
    drawArc(
        color = borderColor,
        style = Stroke(strokeWidth.toPx(), cap = cap),
        startAngle = 180 - sweepAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        size = Size(mCurve , mCurve ),
        topLeft = Offset(
            0f, height - mCurve
        )
    )

    drawArc(
        color = borderColor,
        style = Stroke(strokeWidth.toPx(), cap = cap),
        startAngle = 180f,
        sweepAngle = sweepAngle,
        useCenter = false,
        size = Size(mCurve , mCurve ),
        topLeft = Offset(
            0f, 0f
        )
    )

    drawArc(
        color = borderColor,
        style = Stroke(strokeWidth.toPx(), cap = cap),
        startAngle = 270 - sweepAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        size = Size(mCurve , mCurve ),
        topLeft = Offset(
            0f, 0f
        )
    )


    drawArc(
        color = borderColor,
        style = Stroke(strokeWidth.toPx(), cap = cap),
        startAngle = 270f,
        sweepAngle = sweepAngle,
        useCenter = false,
        size = Size(mCurve, mCurve ),
        topLeft = Offset(
            width - mCurve , 0f
        )
    )

    drawArc(
        color = borderColor,
        style = Stroke(strokeWidth.toPx(), cap = cap),
        startAngle = 360 - sweepAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        size = Size(mCurve , mCurve ),
        topLeft = Offset(
            width - mCurve , 0f
        )
    )


//    drawLine(
//        SolidColor(borderColor), Offset(width, height - mCapSize), Offset(width, height - curvePx),
//        strokeWidth.toPx(), lineCap,
//    )
//
//    drawLine(
//        SolidColor(borderColor), Offset(width - mCapSize, height), Offset(width - curvePx, height),
//        strokeWidth.toPx(), lineCap,
//    )
//
//    drawLine(
//        SolidColor(borderColor), Offset(mCapSize, height), Offset(curvePx, height),
//        strokeWidth.toPx(), lineCap,
//    )
//
//    drawLine(
//        SolidColor(borderColor), Offset(0f, height - curvePx), Offset(0f, height - mCapSize),
//        strokeWidth.toPx(), lineCap
//    )
//
//    drawLine(
//        SolidColor(borderColor), Offset(0f, curvePx), Offset(0f, mCapSize),
//        strokeWidth.toPx(), lineCap,
//    )
//
//    drawLine(
//        SolidColor(borderColor), Offset(curvePx, 0f), Offset(mCapSize, 0f),
//        strokeWidth.toPx(), lineCap,
//    )
//
//    drawLine(
//        SolidColor(borderColor), Offset(width - curvePx, 0f), Offset(width - mCapSize, 0f),
//        strokeWidth.toPx(), lineCap,
//    )
//
//    drawLine(
//        SolidColor(borderColor), Offset(width, curvePx), Offset(width, mCapSize),
//        strokeWidth.toPx(), lineCap
//    )
}


/**
 * Draw static and dynamic circles when user drag
 */
@Composable
fun GooeyEffectSample2() {
    val pathDynamic: Path = remember { Path() }
    val pathStatic: Path = remember { Path() }

    /**
     * Current position of the pointer that is pressed or being moved
     */
    var currentPosition by remember { mutableStateOf(Offset.Unspecified) }

    val segmentCount = 20
    val pathMeasure = remember {
        PathMeasure()
    }

    val modifier: Modifier = Modifier
        .pointerInput(Unit) {
            detectDragGestures { change, _ ->
                currentPosition = change.position
            }
        }
        .size(200.dp)

    val paint: Paint = remember {
        Paint()
    }

    var isPaintSetUp: Boolean by remember {
        mutableStateOf(false)
    }

    Canvas(modifier = modifier) {
        val center: Offset = size.center

        val position: Offset = if (currentPosition == Offset.Unspecified) {
            center
        } else currentPosition

        pathDynamic.reset()
        pathDynamic.addOval(
            Rect(
                center = position,
                radius = 150f
            )
        )

        pathStatic.reset()
        pathStatic.addOval(
            Rect(
                center = Offset(center.x, center.y),
                radius = 100f
            )
        )

        pathMeasure.setPath(pathDynamic, true)

        val discretePathEffect = DiscretePathEffect(pathMeasure.length / segmentCount, 0f)
        val cornerPathEffect = CornerPathEffect(50f)

        val chainPathEffect: PathEffect = PathEffect.chainPathEffect(
            outer = cornerPathEffect.toComposePathEffect(),
            inner = discretePathEffect.toComposePathEffect()
        )

        if (!isPaintSetUp) {
            paint.shader = LinearGradientShader(
                from = Offset.Zero,
                to = Offset(size.width, size.height),
                colors = listOf(
                    Color(0xffFFEB3B),
                    Color(0xffE91E63)
                ),
                tileMode = TileMode.Clamp
            )
            isPaintSetUp = true
            paint.pathEffect = chainPathEffect
        }

        val newPath: Path = Path.combine(PathOperation.Union, pathDynamic, pathStatic)

        with(drawContext.canvas) {
            this.drawPath(
                newPath,
                paint
            )
        }
    }
}

@Composable
fun DrawBrackets() {
    Canvas(
        modifier = Modifier
            .size(200.dp)
    ) {
        val width = size.width
        val height = size.height
        val halfWidth = width.times(.5f)
        val halfHeight = height.times(.5f)

        val startPoints = listOf(
            PointF(0f, 0f),
            PointF(0f, halfHeight),
            PointF(0f, height),

            PointF(width, 0f),
            PointF(width, halfHeight),
            PointF(width, height),
        )

        val path = Path().apply {
            startPoints.forEach { point ->

                val lineEndX =
                    if (point.x > halfWidth)
                        width.minus(halfWidth.times(.3f))
                    else
                        halfWidth.times(.3f)

                val curveXPart1 =
                    if (point.x > halfWidth)
                        width.minus(halfWidth.times(.5f))
                    else
                        halfWidth.times(.5f)

                val curveXPart2 =
                    if (point.x > halfWidth)
                        width.minus(halfWidth.times(.7f))
                    else
                        halfWidth.times(.7f)

                val curve1 = PointF(curveXPart1, point.y)
                val curve2 = PointF(curveXPart1, halfHeight - ((halfHeight - point.y) / 2))

                // curve1.x > lineEndX
                // curve1.y == point.y
                // curve2.x == curveXPart1
                // curve2.y < halfHeight
                moveTo(point.x, point.y)
                lineTo(lineEndX, point.y)
                quadraticBezierTo(
                    curve1.x,
                    curve1.y,
                    curve2.x,
                    curve2.y,
                )
                quadraticBezierTo(
                    curveXPart1,
                    halfHeight,
                    curveXPart2,
                    halfHeight,
                )
                lineTo(halfWidth, halfHeight)
            }
        }
        drawPath(
            path = path,
            color = Color.Black,
            style = Stroke(width = 20f, cap = StrokeCap.Round)
        )
    }
}

@Composable
fun DrawScope.drawBorderWithThinRadius() {
    val density = LocalDensity.current
    val width = size.width
    val height = size.height
    val borderWidth = with(density) {
        8.dp.toPx()
    }
    val radius = with(density) {
        96.dp.toPx()
    }

    // left
    drawLine(
        brush = SolidColor(Color.Black),
        start = Offset(0f, radius),
        end = Offset(0f, height - radius),
        strokeWidth = borderWidth,
        cap = StrokeCap.Round,
    )
    // bottom
    drawLine(
        brush = SolidColor(Color.Black),
        start = Offset(radius, height),
        end = Offset(width - radius, height),
        strokeWidth = borderWidth,
        cap = StrokeCap.Round,
    )
    // right
    drawLine(
        brush = SolidColor(Color.Black),
        start = Offset(width, height - radius),
        end = Offset(width, radius),
        strokeWidth = borderWidth,
        cap = StrokeCap.Round,
    )
    // top
    drawLine(
        brush = SolidColor(Color.Black),
        start = Offset(radius, 0f),
        end = Offset(width - radius, 0f),
        strokeWidth = borderWidth,
        cap = StrokeCap.Round,
    )
    // draw radius left top
    drawPath(
        path = Path().apply {
            moveTo(0f, radius)
            cubicTo(
                0f, radius.times(0.3f),
                radius.times(0.3f), 0f,
                radius, 0f
            )
        },
        color = Color.Black,
        style = Stroke(
            width = borderWidth,
            miter = borderWidth,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        ),
    )
    // draw radius right top
    drawPath(
        path = Path().apply {
            moveTo(width - radius, 0f)
            cubicTo(
                width - radius.times(0.3f), 0f,
                width, radius.times(0.3f),
                width, radius
            )
        },
        color = Color.Black,
        style = Stroke(borderWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)
    )
    // draw radius left bottom
    drawPath(
        path = Path().apply {
            moveTo(0f, height - radius)
            cubicTo(
                0f, height - radius.times(0.3f),
                radius.times(0.3f), height,
                radius, height
            )
        },
        color = Color.Black,
        style = Stroke(
            width = borderWidth,
            miter = 8f,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        ),
    )
    // draw radius right bottom
    drawPath(
        path = Path().apply {
            moveTo(width - radius, height)
            cubicTo(
                width - radius.times(0.3f), height,
                width, height - radius.times(0.3f),
                width, height - radius
            )
        },
        color = Color.Black,
        style = Stroke(borderWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
    )

    val shiftingSpace = borderWidth.times(0.8f)
    val innerBorderWidth = borderWidth.times(0.6f)
    val innerRadius = radius - shiftingSpace.times(2f)

    // inner left
    drawLine(
        brush = SolidColor(Color.Black),
        start = Offset(shiftingSpace, innerRadius + shiftingSpace),
        end = Offset(shiftingSpace, height - innerRadius - shiftingSpace),
        strokeWidth = innerBorderWidth,
        cap = StrokeCap.Round,
    )
    // inner top
    drawLine(
        brush = SolidColor(Color.Black),
        start = Offset(innerRadius + shiftingSpace, shiftingSpace),
        end = Offset(width - innerRadius - shiftingSpace, shiftingSpace),
        strokeWidth = innerBorderWidth,
        cap = StrokeCap.Round,
    )
    // inner bottom - draw from left to right of bottom edge
    drawLine(
        brush = SolidColor(Color.Black),
        start = Offset(innerRadius + shiftingSpace, height - shiftingSpace),
        end = Offset(width - innerRadius - shiftingSpace, height - shiftingSpace),
        strokeWidth = innerBorderWidth,
        cap = StrokeCap.Round,
    )
    // inner right - draw from bottom to top of right edge
    drawLine(
        brush = SolidColor(Color.Black),
        start = Offset(width - shiftingSpace, height - innerRadius - shiftingSpace),
        end = Offset(width - shiftingSpace, innerRadius + shiftingSpace),
        strokeWidth = innerBorderWidth,
        cap = StrokeCap.Round,
    )
    // draw inner radius left top
    drawPath(
        path = Path().apply {
            moveTo(innerRadius + shiftingSpace, shiftingSpace)
            cubicTo(
                (innerRadius + shiftingSpace).times(0.3f), shiftingSpace,
                shiftingSpace, (innerRadius + shiftingSpace).times(0.3f),
                shiftingSpace, innerRadius + shiftingSpace,
            )
        },
        color = Color.Black,
        style = Stroke(
            width = innerBorderWidth,
            miter = innerBorderWidth,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
    )
    // draw inner radius right top
    drawPath(
        path = Path().apply {
            moveTo(width - innerRadius - shiftingSpace, shiftingSpace)
            cubicTo(
                width - (innerRadius + shiftingSpace).times(0.3f), shiftingSpace,
                width - shiftingSpace, (innerRadius + shiftingSpace).times(0.3f),
                width - shiftingSpace, innerRadius + shiftingSpace,
            )
        },
        color = Color.Black,
        style = Stroke(
            width = innerBorderWidth,
            miter = innerBorderWidth,
            cap = StrokeCap.Round,
            join = StrokeJoin.Bevel
        )
    )
    // draw inner radius left bottom
    drawPath(
        path = Path().apply {
            moveTo(shiftingSpace, height - innerRadius - shiftingSpace)
            cubicTo(
                shiftingSpace, height - (innerRadius + shiftingSpace).times(0.3f),
                (shiftingSpace + innerRadius).times(0.3f), height - shiftingSpace,
                shiftingSpace + innerRadius, height - shiftingSpace,
            )
        },
        color = Color.Black,
        style = Stroke(
            width = innerBorderWidth,
            miter = innerBorderWidth,
            cap = StrokeCap.Round,
            join = StrokeJoin.Bevel
        )
    )
    // draw inner radius right bottom
    drawPath(
        path = Path().apply {
            moveTo(width - innerRadius - shiftingSpace, height - shiftingSpace)
            cubicTo(
                width - (innerRadius + shiftingSpace).times(0.3f), height - shiftingSpace,
                width - shiftingSpace, height - (innerRadius + shiftingSpace).times(0.3f),
                width - shiftingSpace, height - innerRadius - shiftingSpace,
            )
        },
        color = Color.Black,
        style = Stroke(
            width = innerBorderWidth,
            miter = innerBorderWidth,
            cap = StrokeCap.Round,
            join = StrokeJoin.Bevel
        )
    )
}

@Composable
fun DrawCubic() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        val density = LocalDensity.current.density

        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp

        val screenWidthInPx = screenWidth.value * density
        val screenHeightInPx = configuration.screenHeightDp.dp.value * density

        // (x0, y0) is initial coordinate where path is moved with path.moveTo(x0,y0)
        var x0 by remember { mutableStateOf(0f) }
        var y0 by remember { mutableStateOf(0f) }

        /*
        Adds a cubic bezier segment that curves from the current point(x0,y0) to the
        given point (x3, y3), using the control points (x1, y1) and (x2, y2).
     */
        var x1 by remember { mutableStateOf(0f) }
        var y1 by remember { mutableStateOf(screenWidthInPx) }

        var x2 by remember { mutableStateOf(screenWidthInPx/2) }
        var y2 by remember { mutableStateOf(0f) }

        var x3 by remember { mutableStateOf(screenWidthInPx) }
        var y3 by remember { mutableStateOf(screenHeightInPx) }

        val path = remember { Path() }
        Canvas(
            modifier = Modifier
                .padding(8.dp)
                .shadow(1.dp)
                .background(Color.White)
                .size(screenWidth, screenWidth/2)
        ) {
            path.reset()
            path.moveTo(x0, y0)
            path.cubicTo(x1 = x1, y1 = y1, x2 = x2, y2 = y2, x3 = x3, y3 = y3)


            drawPath(
                color = Color.Green,
                path = path,
                style = Stroke(
                    width = 3.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                )
            )

            // Draw Control Points on screen
            drawPoints(
                listOf(Offset(x1, y1), Offset(x2, y2)),
                color = Color.Green,
                pointMode = PointMode.Points,
                cap = StrokeCap.Round,
                strokeWidth = 40f
            )
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {

            Text(text = "X0: ${x0.roundToInt()}")
            Slider(
                value = x0,
                onValueChange = { x0 = it },
                valueRange = 0f..screenWidthInPx,
            )

            Text(text = "Y0: ${y0.roundToInt()}")
            Slider(
                value = y0,
                onValueChange = { y0 = it },
                valueRange = 0f..screenWidthInPx,
            )

            Text(text = "X1: ${x1.roundToInt()}")
            Slider(
                value = x1,
                onValueChange = { x1 = it },
                valueRange = 0f..screenWidthInPx,
            )

            Text(text = "Y1: ${y1.roundToInt()}")
            Slider(
                value = y1,
                onValueChange = { y1 = it },
                valueRange = 0f..screenWidthInPx,
            )

            Text(text = "X2: ${x2.roundToInt()}")
            Slider(
                value = x2,
                onValueChange = { x2 = it },
                valueRange = 0f..screenWidthInPx,
            )

            Text(text = "Y2: ${y2.roundToInt()}")
            Slider(
                value = y2,
                onValueChange = { y2 = it },
                valueRange = 0f..screenWidthInPx,
            )

            Text(text = "X3: ${x3.roundToInt()}")
            Slider(
                value = x3,
                onValueChange = { x3 = it },
                valueRange = 0f..screenWidthInPx,
            )

            Text(text = "Y3: ${y3.roundToInt()}")
            Slider(
                value = y3,
                onValueChange = { y3 = it },
                valueRange = 0f..screenWidthInPx,
            )
        }
    }
}
