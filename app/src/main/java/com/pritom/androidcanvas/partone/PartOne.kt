package com.pritom.androidcanvas.partone

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun BasicShapes() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {

        //Draw Rectangle
//        val width = 500f
//        val height = 200f
//
//        drawRect(
//            color = Color.White,
//            size = Size(width = width, height = height),
//            topLeft = Offset(x = center.x - width / 2f, y = center.y - height / 2f),
//            style = Stroke(
//                width = 5f,
//            ),
//        )

        //Draw Circle

        drawCircle(
            color = Color.Red,
            radius = 200f,
            style = Stroke(
                width = 10f
            )
        )

        drawCircle(
            color = Color.Green,
            radius = 100f,
            center = Offset(x = 100f, y = 100f)
        )

        drawCircle(
            radius = 200f,
            center = Offset(x = 400f, y = 400f),
            brush = Brush.radialGradient(
                colors = listOf(Color.Green, Color.Yellow, Color.Blue),
                radius = 180f,
                center = Offset(x = 400f, y = 400f)
            )
        )
    }
}

@Preview
@Composable
fun BasicShapesPreview() {
    BasicShapes()
}