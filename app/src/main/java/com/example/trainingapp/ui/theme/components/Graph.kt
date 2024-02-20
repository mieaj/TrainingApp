package com.example.trainingapp.ui.theme.components

import android.graphics.Paint
import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.sp
import com.example.trainingapp.ui.theme.MediumPadding
import com.example.trainingapp.ui.theme.Shapes

@Composable
fun Graph(
    modifier: Modifier = Modifier,
    points: List<Float> = listOf(10f, 30f, 25f, 15f, 20f)
) {
    val rangeArea = if (points.max() - points.min() > 10) 5 else 1
    val xValues = ((1..10))
    val yValues =
        (points.min().toInt() - rangeArea..points.max().toInt() + rangeArea step rangeArea)
    val pointColor = MaterialTheme.colorScheme.primary
    val background = MaterialTheme.colorScheme.surface
    val coordinateSystemColor = MaterialTheme.colorScheme.onSurface
    val numberLineColor = MaterialTheme.colorScheme.onSurface
    val lineColor = MaterialTheme.colorScheme.onBackground

    Box(
        modifier = modifier
            .background(background, shape = Shapes.medium)
            .padding(MediumPadding),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            val axisSpace = PointF(size.width / xValues.count(), size.height / yValues.count())
            val startPoint = PointF(0f, size.height)
            val axisOffset = 50f
            val numberOffset = 1f

            drawCoordinateSystem(
                color = coordinateSystemColor,
                axisOffset = axisOffset,
                startPointF = startPoint
            )
            drawCoordinateNumber(
                color = numberLineColor,
                numberOffset = numberOffset,
                xValues = xValues,
                yValues = yValues,
                axisOffset = axisOffset,
                startPoint = startPoint,
                axisSpace = axisSpace
            )
            /** placing points */
            /** placing points */
            /** placing points */

            /** placing points */
            var currentPoint: Pair<Float, Float>? = null
            points.forEachIndexed { i, value ->
                val x1 = axisSpace.x * (i + numberOffset)
                val y1 =
                    startPoint.y - ((axisSpace.y / rangeArea) * (value - yValues.min() + rangeArea))
                drawCircle(
                    color = pointColor,
                    radius = 12f,
                    center = Offset(x1, y1)
                )
                currentPoint?.let { (x, y) ->
                    drawLine(
                        color = lineColor,
                        start = Offset(x, y),
                        end = Offset(x1, y1),
                        strokeWidth = 4f
                    )
                }
                currentPoint = x1 to y1
            }

        }
    }

}

fun DrawScope.drawCoordinateSystem(color: Color, axisOffset: Float, startPointF: PointF) {
    val lineSize = 6f
//    Add axisOffset to y start value if is added
    drawLine(
        color = color,
        start = Offset(startPointF.x + axisOffset, startPointF.y),
        end = Offset(startPointF.x + axisOffset, 0f),
        strokeWidth = lineSize
    )
//    Add axisOffset to y value if is added
    drawLine(
        color = color,
        start = Offset(startPointF.x + axisOffset, startPointF.y),
        end = Offset(size.width, startPointF.y),
        strokeWidth = lineSize
    )
}

fun DrawScope.drawCoordinateNumber(
    color: Color,
    numberOffset: Float,
    xValues: IntRange,
    yValues: IntProgression,
    axisOffset: Float,
    startPoint: PointF,
    axisSpace: PointF
) {
    val centerNumber = 0.1f
    val textPaint = Paint().apply {
        textSize = 12.sp.toPx()
        setColor(color.toArgb())
    }
//    /** placing x axis points */
//    xValues.forEachIndexed { i, value ->
//        drawContext.canvas.nativeCanvas.drawText(
//            "$value",
//            axisSpace.x * (i + numberOffset-centerNumber),
//            startPoint.y,
//            textPaint
//        )
//    }
    /** placing y axis points */
    fun modulus(index: Int) = if (yValues.count() > 10) index % 2 != 0 else true
    yValues.forEachIndexed { i, value ->
        if (modulus(i)) {
            drawContext.canvas.nativeCanvas.drawText(
                "$value",
                startPoint.x,
                (startPoint.y - axisSpace.y * (i + numberOffset - centerNumber)),
                textPaint
            )
        }
        drawLine(
            color = color,
            start = Offset(
                startPoint.x + axisOffset,
                startPoint.y - axisSpace.y * (i + numberOffset)
            ),
            end = Offset(
                size.width,
                startPoint.y - axisSpace.y * (i + numberOffset)
            ),
            strokeWidth = 2f
        )
    }
}