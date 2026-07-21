package com.pritom.androidcanvas.pie_chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyPieChart(
    points: List<Float>,
    colors: List<Color>,
) {
    val total = points.sum()

    val proportions = points.map {
        it * 100 / total
    }

    val sweepAnglePercentages = proportions.map {
        360 * it / 100
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        var startAngle = 270f
        sweepAnglePercentages.forEachIndexed { index, sweepAngle ->
            drawA(
                color = colors[index],
                startAngle = startAngle,
                sweepAngle = sweepAngle,
            )
            startAngle += sweepAngle
        }
    }
}


fun DrawScope.drawA(
    color: Color,
    startAngle: Float,
    sweepAngle: Float,
) {
    val padding = 350f
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        size = Size(width = size.width - padding, height = size.width - padding),
        style = Stroke(
            width = 240f,
        ),
        topLeft = Offset(padding / 2f, padding / 2f)
    )
}

@Preview
@Composable
fun MyPieChartPreview() {
    MyPieChart(
        points = listOf(105f, 35f, 200f),
        colors = listOf(Color.Red, Color.Green, Color.Blue),
    )
}