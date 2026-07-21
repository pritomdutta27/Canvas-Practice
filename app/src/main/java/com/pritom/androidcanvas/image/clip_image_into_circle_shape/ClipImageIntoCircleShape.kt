package com.pritom.androidcanvas.image.clip_image_into_circle_shape

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.pritom.androidcanvas.R


@Preview
@Composable
fun ClipImageIntoCircle() {
    val context = LocalContext.current
    val drawable = context.getDrawable(R.drawable.ic_launcher_background)
    val bitmap = drawable?.toBitmap()
    val image = bitmap?.asImageBitmap()

    Canvas(
        modifier = Modifier
            .size(200.dp)
            .border(2.dp, color = Color.Yellow)
            .padding(16.dp)
    ) {
        val canvasMinDimension = size.minDimension / 2

        val path = Path().apply {
            addOval(Rect(center = center, radius = canvasMinDimension))
        }

        clipPath(path) {
            image?.let {
                drawImage(
                    image = it,
                    dstSize = IntSize(
                        width = size.width.toInt(),
                        height = size.height.toInt()
                    )
                )
            }
        }
    }
}