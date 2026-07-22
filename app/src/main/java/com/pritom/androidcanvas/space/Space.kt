package com.pritom.androidcanvas.space

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun INeedSpace() {
    Column(
        modifier =
            Modifier
                .padding(INeedSpace.padding)
                .drawBehind {
                    drawRoundRect(
                        size = size,
                        cornerRadius = INeedSpace.shape,
                        brush =
                            Brush.radialGradient(
                                colors = Colors.spaceGradient,
                                radius = size.width * 0.75f,
                            ),
                    )
                },
    ) {
        val infiniteTransition = rememberInfiniteTransition(label = "infinite")

        val animationDuration = 3000
        val animationEasing = EaseIn

        val starSizeMultiplierOne by infiniteTransition
            .animateFloat(
                initialValue = 1f,
                targetValue = 1.5f,
                animationSpec =
                    infiniteRepeatable(
                        animation =
                            tween(
                                durationMillis = animationDuration,
                                easing = animationEasing,
                            ),
                        repeatMode = RepeatMode.Reverse,
                    ),
                label = "starSizeMultiplierOne",
            )

        val starSizeMultiplierTwo by infiniteTransition
            .animateFloat(
                initialValue = 0.7f,
                targetValue = 1.7f,
                animationSpec =
                    infiniteRepeatable(
                        animation =
                            tween(
                                durationMillis = animationDuration - 700,
                                easing = animationEasing,
                            ),
                        repeatMode = RepeatMode.Reverse,
                    ),
                label = "starSizeMultiplierTwo",
            )

        val degrees by infiniteTransition
            .animateFloat(
                initialValue = 360f,
                targetValue = 0f,
                animationSpec =
                    infiniteRepeatable(
                        animation =
                            tween(
                                durationMillis = animationDuration * 6,
                                easing = LinearEasing,
                            ),
                    ),
                label = "degrees",
            )

        val centerOffset by infiniteTransition
            .animateFloat(
                initialValue = 0f,
                targetValue = 2f,
                animationSpec =
                    infiniteRepeatable(
                        animation =
                            tween(
                                durationMillis = animationDuration,
                                easing = EaseIn,
                            ),
                        repeatMode = RepeatMode.Reverse,
                    ),
                label = "centerOffset",
            )
        Canvas(
            modifier =
                Modifier
                    .width(INeedSpace.canvasWidth)
                    .height(INeedSpace.canvasHeight)
                    .padding(INeedSpace.canvasPadding),
        ) {
            val outlineStyle =
                Stroke(
                    width = 4f,
                )

            val starsList = getStars(size)

            val stars =
                starsList.mapIndexed { index, star ->
                    val multiplier =
                        if (index % 2 == 0) {
                            starSizeMultiplierOne
                        } else {
                            starSizeMultiplierTwo
                        }

                    star.copy(
                        size = star.size * multiplier,
                    )
                }

            drawSaturn(
                center =
                    Offset(
                        size.width * 0.25f + centerOffset,
                        size.height * 0.25f + centerOffset,
                    ),
                outlineStyle = outlineStyle,
            )

            drawPlanet(
                center = Offset(center.x + 180f, center.y + 80f),
                outlineStyle = outlineStyle,
                degrees = degrees,
            )

            stars.forEach { (starSize, offset, color) ->
                drawStar(
                    starSize = Size(starSize, starSize),
                    color = color,
                    center = offset,
                    outlineStyle =
                        Stroke(
                            width = 2f,
                        ),
                )
            }
        }
    }
}

fun DrawScope.drawSaturn(
    center: Offset,
    outlineStyle: Stroke,
) {
    drawCircle(
        center = center,
        color = Colors.white,
        radius = 100f,
        style = outlineStyle,
    )

    drawArc(
        topLeft = Offset(center.x - 80f, center.y - 80f),
        color = Colors.white,
        startAngle = 180f,
        sweepAngle = 90f,
        useCenter = false,
        style =
            Stroke(
                width = 2f,
            ),
        size = Size(160f, 160f),
    )

    rotate(40f, center) {
        drawArc(
            color = Colors.white,
            startAngle = 217f,
            sweepAngle = 285f,
            useCenter = false,
            topLeft = Offset(center.x - 50f, center.y - 150f),
            style = outlineStyle,
            size =
                Size(
                    100f,
                    300f,
                ),
        )
    }
}

fun DrawScope.drawPlanet(
    center: Offset,
    outlineStyle: Stroke,
    degrees: Float,
) {
    drawCircle(
        center = center,
        color = Colors.white,
        radius = 80f,
        style = outlineStyle,
    )

    drawMoon(center, outlineStyle, degrees)

    drawPath(
        path =
            Path().apply {
                moveTo(center.x - 82f, center.y)
                quadraticTo(center.x - 45f, center.y + 5f, center.x, center.y)
            },
        color = Colors.white,
        style =
            Stroke(
                width = 3f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(60f, 10f, 50f, 10f), 0f),
            ),
    )

    drawPath(
        path =
            Path().apply {
                moveTo(center.x - 78f, center.y + 25f)
                quadraticTo(center.x - 45f, center.y + 30f, center.x + 10f, center.y + 25f)
            },
        color = Colors.white,
        style =
            Stroke(
                width = 3f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(60f, 10f, 50f, 10f), 0f),
            ),
    )

    drawPath(
        path =
            Path().apply {
                moveTo(center.x - 65f, center.y + 50f)
                quadraticTo(center.x - 50f, center.y + 55f, center.x - 20f, center.y + 52f)
            },
        color = Colors.white,
        style =
            Stroke(
                width = 3f,
            ),
    )
}

fun DrawScope.drawMoon(
    center: Offset,
    outlineStyle: Stroke,
    degrees: Float,
) {
    rotate(degrees = degrees, pivot = center) {
        drawCircle(
            center = Offset(center.x - 100f, center.y + 80f),
            color = Colors.white,
            radius = 15f,
            style = outlineStyle,
        )

        drawArc(
            topLeft = Offset(center.x - 125f, center.y - 125f),
            color = Colors.white,
            startAngle = 160f,
            sweepAngle = 200f,
            useCenter = false,
            style =
                Stroke(
                    width = 2f,
                    pathEffect =
                        PathEffect.dashPathEffect(
                            floatArrayOf(160f, 70f, 50f, 80f, 40f, 40f),
                            0f,
                        ),
                ),
            size = Size(250f, 250f),
        )
    }
}

fun DrawScope.drawStar(
    starSize: Size,
    center: Offset,
    color: Color,
    outlineStyle: Stroke,
) {
    val path = Path()

    path.moveTo(center.x, center.y - starSize.height * 0.5f)
    path.quadraticTo(
        center.x,
        center.y,
        center.x + starSize.width / 2,
        center.y,
    )

    path.quadraticTo(
        center.x,
        center.y,
        center.x,
        center.y + starSize.height / 2,
    )
    path.quadraticTo(
        center.x,
        center.y,
        center.x - starSize.width / 2,
        center.y,
    )
    path.quadraticTo(
        center.x,
        center.y,
        center.x,
        center.y - starSize.height / 2,
    )

    drawPath(
        path = path,
        color = color,
        style = outlineStyle,
    )
}

fun getStars(size: Size) =
    listOf(
        Star(
            30f,
            Offset(size.width * 0.5f, size.height * 0.5f),
            Colors.stars[0],
        ),
        Star(
            20f,
            Offset(
                size.width * 0.7f,
                size.height,
            ),
            Colors.stars[1],
        ),
        Star(
            20f,
            Offset(
                25f,
                20f,
            ),
            Colors.stars[2],
        ),
        Star(
            20f,
            Offset(
                size.width * 0.8f,
                60f,
            ),
            Colors.stars[0],
        ),
        Star(
            30f,
            Offset(
                4f,
                size.height * 0.5f,
            ),
            Colors.stars[2],
        ),
        Star(
            20f,
            Offset(
                60f,
                size.height - 60f,
            ),
            Colors.stars[2],
        ),
        Star(
            20f,
            Offset(
                size.width * 0.2f,
                size.height * 0.6f,
            ),
            Colors.stars[2],
        ),
        Star(
            20f,
            Offset(
                size.width * 0.8f,
                60f,
            ),
            Colors.stars[1],
        ),
        Star(
            40f,
            Offset(
                size.width * 0.96f,
                size.height - 40f,
            ),
            Colors.stars[1],
        ),
        Star(
            40f,
            Offset(
                size.width * 0.35f,
                size.height * 0.8f,
            ),
            Colors.stars[1],
        ),
        Star(
            40f,
            Offset(
                size.width * 0.6f,
                100f,
            ),
            Colors.stars[0],
        ),
        Star(
            20f,
            Offset(
                size.width * 0.25f,
                size.height - 20f,
            ),
            Colors.stars[0],
        ),
        Star(
            10f,
            Offset(
                size.width - 40f,
                size.height * 0.5f - 30f,
            ),
            Colors.stars[0],
        ),
        Star(
            10f,
            Offset(
                (size.width * 0.5f) - 20f,
                20f,
            ),
            Colors.stars[0],
        ),
        Star(
            10f,
            Offset(
                size.width,
                30f,
            ),
            Colors.stars[2],
        ),
    )

object Colors {
    val spaceGradient =
        listOf(
            Color(0xFF02010a),
            Color(0xFF04052e),
            Color(0xFF140152),
            Color(0xFF22007c),
            Color.Transparent,
        )

    val stars =
        listOf(
            Color(0xFFF6E8B7),
            Color(0xFFE8C547),
            Color(0xFFEFD780),
        )

    val white = Color(0xFFE8E8E8)
}

data class Star(
    val size: Float,
    val topLeft: Offset,
    val color: Color,
)

object INeedSpace {
    val padding = 12.dp
    val shape = CornerRadius(44f)
    val canvasWidth = 300.dp
    val canvasHeight = 200.dp
    val canvasPadding = 12.dp
}