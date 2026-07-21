package com.pritom.androidcanvas.donut

import android.graphics.ComposePathEffect
import android.graphics.CornerPathEffect
import android.graphics.DiscretePathEffect
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import kotlin.random.Random

private val SprinkleColors = listOf(
    Color(0xFF4998DF),
    Color(0xFFE40542),
    Color(0xFF222F5B),
    Color(0xFFFF4D00),
    Color(0xFFE38117),
    Color(0xFF5F9A69),
    Color(0xFFD59CC2),
    Color(0xFF00FF00),
)

@Composable
fun SweetDonut(
    modifier: Modifier = Modifier,
    baseColor: Color = Color(0xFFEB768B),
    icingColor: Color = Color(0xFFFFDECD),
) {

    // Generate sprinkles only once
    val sprinkles = remember {
        List(200) {
            Sprinkle(
                angle = Random.nextFloat() * 360f,
                sweep = Random.nextInt(3, 6).toFloat(),
                stroke = Random.nextInt(3, 6).toFloat(),
                offset = Random.nextInt(-300, 300).toFloat(),
                color = SprinkleColors.random(),
            )
        }
    }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
    ) {

        val donutRadius = size.minDimension / 2.5f
        val holeRadius = donutRadius * 0.3f

        val donutPath = Path().apply {
            addOval(
                Rect(
                    center = center,
                    radius = donutRadius,
                ),
            )
        }

        // Base donut
        drawPath(
            path = donutPath,
            color = baseColor,
        )

        // Icing
        drawContext.canvas.nativeCanvas.drawCircle(
            center.x,
            center.y,
            donutRadius - 20f,
            Paint().apply {
                color = icingColor.toArgb()
                pathEffect = ComposePathEffect(
                    CornerPathEffect(100f),
                    DiscretePathEffect(60f, 15f),
                )
            },
        )

        // Sprinkles
        clipPath(donutPath) {
            sprinkles.forEach {
                drawArc(
                    color = it.color,
                    startAngle = it.angle,
                    sweepAngle = it.sweep,
                    useCenter = false,
                    style = Stroke(it.stroke),
                    topLeft = Offset(
                        center.x - it.offset,
                        center.y - it.offset,
                    ),
                    size = Size(
                        width = size.width / 2,
                        height = size.height / 2,
                    ),
                )
            }
        }

        // Donut hole
        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                center.x,
                center.y,
                holeRadius,
                Paint().apply {
                    this.color = android.graphics.Color.WHITE
                    setShadowLayer (100f, 0f, -30f, baseColor.toArgb() )
                },
            )
        }
    }
}

private data class Sprinkle(
    val angle: Float,
    val sweep: Float,
    val stroke: Float,
    val offset: Float,
    val color: Color,
)

@Preview
@Composable
fun SweetDonutPreview() {
    SweetDonut()
}