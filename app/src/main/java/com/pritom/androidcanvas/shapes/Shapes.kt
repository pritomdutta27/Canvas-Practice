package com.pritom.androidcanvas.shapes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun DrawCircle(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .size(200.dp)
            .border(5.dp, color = Color.Red)
            .padding(16.dp),
    ) {

        /*
        * Draw Circle
        * */
        drawCircle(
            color = Color.Blue,
            radius = 200f
        )

    }
}

@Preview
@Composable
fun DrawRectangle(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .size(200.dp)
            .border(5.dp, color = Color.Red)
            .padding(16.dp),
    ) {

        /*
        * Draw Rectangle
        * */
        drawRect(
            color = Color.Green,
            size = Size(width = 400f, height = 400f),
            topLeft = Offset(x = center.x - 200f, y = center.y - 200f)
        )
    }
}


@Preview
@Composable
fun DrawLine(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .size(200.dp)
            .border(5.dp, color = Color.Red)
            .padding(16.dp),
    ) {

        /*
        * Draw Line
        * */
        drawLine(
            color = Color.Yellow,
            start = Offset(x = size.width / 2, y = 0f),
            end = Offset(x = size.width / 2, y = size.height),
        )
    }
}

@Preview
@Composable
fun DrawArc(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .size(200.dp)
            .border(5.dp, color = Color.Red)
            .padding(16.dp),
    ) {

        /*
        * Draw Arc
        * */
        drawArc(
            color = Color.Yellow,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
//            style = Stroke(width = 3.dp.toPx())
        )
    }
}


@Preview
@Composable
fun RectangleRotate(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .size(200.dp)
            .border(5.dp, color = Color.Red)
            .padding(16.dp),
    ) {

        /*
        * Rectangle Rotate
        * */
        rotate(degrees = 45f) {
            drawRect(
                color = Color.Red,
                size = Size(width = 300f, height = 300f),
                topLeft = Offset(x = center.x - 150f, y = center.y - 150f)
            )
        }
    }
}

@Preview
@Composable
fun RectangleBrush(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .size(200.dp)
            .border(5.dp, color = Color.Red)
            .padding(16.dp),
    ) {

        /*
        * Rectangle Brush
        * */
        val brush = Brush.linearGradient(
            colors = listOf(
                Color.Red,
                Color.Blue,
                Color.Green,
                Color.Yellow
            ),
        )

        drawRect(
            brush,
            size = Size(width = 300f, height = 300f),
            topLeft = Offset(x = center.x - 150f, y = center.y - 150f)
        )

    }
}

@Preview
@Composable
fun TextCanvas(modifier: Modifier = Modifier) {
    val textMeasurer = rememberTextMeasurer()
    Canvas(
        modifier = modifier
            .size(200.dp)
            .border(5.dp, color = Color.Red)
            .padding(16.dp),
    ) {

        /*
        * Text Canvas
        * */
        drawText(
            textMeasurer,
            "Hello World",
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp
            ),
            topLeft = Offset(x = center.x - 150f, y = center.y - 50f)
        )

    }
}