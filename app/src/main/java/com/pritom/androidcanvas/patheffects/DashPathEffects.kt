package com.pritom.androidcanvas.patheffects

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StampedPathEffectStyle
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun DashPathEffect() {

    val dashDot = 35f
    val density = LocalDensity.current

    with(density) {
        val dashPathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashDot.toDp().toPx(), dashDot.toDp().toPx()),
            phase = 0f,
        )

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(20.dp),
        ) {
            drawLine(
                color = Color.White,
                pathEffect = dashPathEffect,
                start = Offset(0f, size.height / 2 - 2),
                end = Offset(size.width, size.height / 2 - 2),
                strokeWidth = 20f,
                cap = StrokeCap.Round,
            )
        }
    }
}


@Preview
@Composable
fun MorseCodeEffect() {

    val dashDot = 4.5f
    val density = LocalDensity.current

    with(density) {

        val dashOnInterval = (dashDot * 4).dp.toPx()
        val dashOffInterval = (dashDot * 4).dp.toPx()
        val dotOnInterval = (dashDot / 2).dp.toPx()
        val dotOffInterval = (dashDot * 4).dp.toPx()

        val dashPathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(
                dashOnInterval,
                dashOffInterval,
                dotOnInterval,
                dotOffInterval,
            ),
        )

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(20.dp),
        ) {
            drawLine(
                color = Color.White,
                pathEffect = dashPathEffect,
                start = Offset(0f, size.height / 2 - 2),
                end = Offset(size.width, size.height / 2 - 2),
                strokeWidth = 25f,
                cap = StrokeCap.Round,
            )
        }
    }
}


@Preview
@Composable
fun CirclePathEffect() {

    val space = 50f
    val density = LocalDensity.current

    with(density) {
        val path = Path().apply {
            addOval(Rect(center = Offset.Zero, radius = 5.dp.toPx()))
        }
        val dashPathEffect = PathEffect.stampedPathEffect(
            shape = path,
            space.toDp().toPx(),
            phase = 20f,
            style = StampedPathEffectStyle.Translate,
        )

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(20.dp),
        ) {
            drawLine(
                color = Color.White,
                pathEffect = dashPathEffect,
                start = Offset(0f, size.height / 2 - 2),
                end = Offset(size.width, size.height / 2 - 2)
            )
        }
    }
}