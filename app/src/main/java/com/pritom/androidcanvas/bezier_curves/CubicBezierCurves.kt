package com.pritom.androidcanvas.bezier_curves

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.withTimeout


@Preview
@Composable
fun CubicBezier() {

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(20.dp),
    ) {

        val path = Path().apply {
            val width = size.width
            val height = size.height

            moveTo(0f, height / 2)
            quadraticTo(200f, 800f, 300f, 300f)
            quadraticTo(width.times(0.4f), 10f, 500f, 300f)
            quadraticTo(600f, 600f, width, height / 2)
        }

        drawPath(path, color = Color.White, style = Stroke(width = 8f))
    }
}