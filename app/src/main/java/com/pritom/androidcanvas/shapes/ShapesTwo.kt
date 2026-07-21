package com.pritom.androidcanvas.shapes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun DrawRoundedRectangle(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .size(200.dp)
            .border(5.dp, color = Color.Red)
            .padding(16.dp)
    ) {
        /*
        * Draw Rounded Rectangle
        * */
        drawRoundRect(
            color = Color.Blue,
            size = Size(width = 300f, height = 300f),
            topLeft = Offset(x = center.x - 150f, y = center.y - 150f),
            cornerRadius = CornerRadius(x = 50f, y = 30f)
        )

        /*
        * Draw Rounded Rectangle
        * with stroke
        * */
        drawRoundRect(
            color = Color.Red,
            size = Size(width = 300f, height = 300f),
            topLeft = Offset(x = center.x - 150f, y = center.y - 150f),
            cornerRadius = CornerRadius(x = 50f, y = 30f),
            style = Stroke(width = 5f)
        )
    }
}


@Preview
@Composable
fun DrawLinePath(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .size(500.dp)
            .border(2.dp, color = Color.White)
            .padding(16.dp)
    ) {
        /*
        * Draw Line
        * */

        drawPath(Path().apply {
            moveTo(0f, size.height / 2)
            lineTo(0f, size.height)
            lineTo(size.width, size.height / 2)
            lineTo(size.width, size.height)
        }, color = Color.Red)
    }
}


@Preview
@Composable
fun DrawSmileFace(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .size(500.dp)
            .border(2.dp, color = Color.White)
            .padding(16.dp)
    ) {
        /*
        * Draw Smile Face
        * */

        drawCircle(
            color = Color.White,
            radius = 50f,
            center = Offset(x = size.width / 3, y = size.height / 2.5f)
        )

        drawCircle(
            color = Color.White,
            radius = 50f,
            center = Offset(x = size.width / 1.5f, y = size.height / 2.5f)
        )

        drawArc(
            color = Color.Cyan,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = false,
            size = size / 3f,
            topLeft = Offset(x = size.width / 3, y = size.height / 3f)
        )
    }
}