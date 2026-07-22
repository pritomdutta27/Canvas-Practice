package com.pritom.androidcanvas.coupon_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun CouponCard(
    offer: String = "30%",
    validTill: String = "",
    strokeColor: Color = Color(0xFFFFFFFF),
    offBackColor: Color = Color(0xFFEAE9E9),
    lineColor: Color = Color(0xFFF9F9F9),
    couponBack: Color = Color(0xFF4CAF50),
    modifier: Modifier = Modifier, // Allow passing in additional modifiers
) {
    Box(
        modifier = modifier
            .background(Color.White)
            .width(300.dp)
            .padding(16.dp)
            .height(120.dp)
            .drawWithCache {
                onDrawWithContent {
                    val width = size.width
                    val height = size.height

                    val radius = 20f

                    val path = Path().apply {
                        moveTo(0f, 0f)

                        /* Vertical Right Circle */
                        for (i in 0..8) {
                            addArc(
                                Rect(center = Offset(width + 2f, height * i / 6f), radius = radius),
                                startAngleDegrees = 90f,
                                sweepAngleDegrees = 180f,
                            )
                        }
                        /* Vertical Left Circle */
                        for (i in 6 downTo 0) {
                            addArc(
                                Rect(center = Offset(-5f, height * i / 6f), radius = radius),
                                startAngleDegrees = -90f,
                                sweepAngleDegrees = 180f,
                            )
                        }
                        close()
                    }

                    drawRect(
                        color = offBackColor,
                        topLeft = Offset.Zero,
                    )
                    drawRect(
                        color = couponBack,
                        topLeft = Offset(size.width / 2.3f, 0f),
                    )

                    // Draw the dashed line
                    val lineOffInterval = size.height / 10f
                    val lineOnInterval = 20f

                    val pathEffect = PathEffect.dashPathEffect(
                        floatArrayOf(lineOnInterval, lineOffInterval),
                    )

                    clipRect {
                        drawPath(path = path, color = strokeColor)

                        drawLine(
                            pathEffect = pathEffect,
                            start = Offset(size.width / 2, 0f),
                            end = Offset(size.width / 2, height),
                            color = lineColor,
                            strokeWidth = 10f,
                            cap = StrokeCap.Round,
                        )
                    }
                    drawContent()
                }
            },
    ) {
        Row(
            modifier = modifier
                .padding(16.dp)
                .fillMaxHeight(),
        ) {
            Column(
                modifier = modifier.weight(1f)
                    .fillMaxHeight()
                    .rotate(-90f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Shopping Coupon",
                    modifier = Modifier,
                    style = TextStyle(
                        fontSize = 5.sp,
                        letterSpacing = TextUnit(3f, TextUnitType.Sp),
                    ),
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = offer,
                    modifier = Modifier,
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }

            Column(
                modifier = modifier.weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){

                Text(
                    text = "PRITOMDUTTA.PRO",
                    modifier = Modifier,
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 5.sp,
                        letterSpacing = TextUnit(2f, TextUnitType.Sp),
                    ),
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = "Coupon",
                    modifier = Modifier.padding(4.dp),
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = "VALID TILL DECEMBER 2026",
                    modifier = Modifier,
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 5.sp,
                        letterSpacing = TextUnit(2f, TextUnitType.Sp),
                    ),
                    textAlign = TextAlign.Center,
                )

            }
        }
    }
}

