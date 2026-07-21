package com.pritom.androidcanvas.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun CanvasAnimation() {
    val colorBg = Color(0xFF475969)
    val colors = listOf(
        Color(0xFFF44336),
        Color(0xFF9C27B0),
        Color(0xFF4CAF50),
        Color(0xFF2196F3),
        Color(0xFFCDDC39),
        Color(0xFFE91E63),
    )

    val infiniteTransition = rememberInfiniteTransition(label = "InfiniteTransition")

    val animationColor by infiniteTransition.animateColor(
        initialValue = colors.random(),
        targetValue = colors.random(),
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(20.dp)
    ) {

        drawRoundRect(
            color = animationColor,
            size = Size(width = 400f, height = 400f),
            topLeft = Offset(x = center.x - 200f, y = center.y - 200f)
        )

    }
}


@Preview
@Composable
fun CanvasBoarderAnimation() {
    val colorBg = Color(0xFFFFFFFF)
    val colors = listOf(
        Color(0xFFF44336),
        Color(0xFF9C27B0),
        Color(0xFF4CAF50),
        Color(0xFF2196F3),
        Color(0xFFCDDC39),
        Color(0xFFE91E63),
    )

    val infiniteTransition = rememberInfiniteTransition(label = "InfiniteTransition")
    val gradientBrush = Brush.sweepGradient(colors = colors)

    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(20.dp)
    ) {
        drawRoundRect(
            color = colorBg,
            size = size,
            style = Stroke(width = 5f),
            cornerRadius = CornerRadius(40f),
        )

        rotate(angle) {
            drawCircle(
                brush = gradientBrush,
                radius = size.width,
                blendMode = BlendMode.SrcIn
            )
        }
    }
}