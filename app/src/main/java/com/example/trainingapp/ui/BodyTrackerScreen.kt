package com.example.trainingapp.ui

import android.graphics.Paint
import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainingapp.R
import com.example.trainingapp.ui.theme.LargePadding
import com.example.trainingapp.ui.theme.MediumPadding
import com.example.trainingapp.ui.theme.Shapes
import com.example.trainingapp.ui.theme.SmallPadding
import com.example.trainingapp.ui.theme.components.OutlinedIconCard
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun BodyTrackerScreen() {
    val items = listOf(
        Triple("Height:", "22", R.drawable.drawing_height),
        Triple("Weight:", "22", R.drawable.weight),
        Triple("Bmi:", "22", R.drawable.drawing_bmi)
    )
    var selectedItem by remember {
        mutableStateOf("none")
    }
    Column {

        LazyVerticalStaggeredGrid(
            modifier = Modifier.padding(MediumPadding),
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = SmallPadding,
            horizontalArrangement = Arrangement.spacedBy(SmallPadding),
            content = {
                items.forEach { (topic, value, icon) ->
                    item {
                        OutlinedIconCard(
                            modifier = Modifier,
                            icon = icon,
                            topic = topic,
                            value = value,
                            selected = topic == selectedItem
                        ) {
                            selectedItem = topic
                        }
                    }
                }
            })
        Graph(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(MediumPadding)
        )
    }
}

@Composable
fun Graph(
    modifier: Modifier = Modifier,
    xValues: List<Int> = listOf(1, 2, 3, 4, 5),
    yValues: List<Int> = listOf(2, 3, 4, 5, 6, 7, 8),
    points: List<Float> = listOf(2f, 3f, 2f, 4f, 8f)
) {
    val pointColor = MaterialTheme.colorScheme.primary
    val background = MaterialTheme.colorScheme.surface
    val coordinateSystemColor = MaterialTheme.colorScheme.onBackground
    val numberLineColor = MaterialTheme.colorScheme.onSurface
    val lineColor = MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = modifier
            .background(background, shape = Shapes.medium)
            .padding(horizontal = LargePadding, vertical = LargePadding),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            val xAxisSpace = size.width / xValues.size
            val yAxisSpace = size.height / yValues.size
            val startPoint = PointF(0f, size.height - 30)
            drawCoordinateSystem(coordinateSystemColor, startPoint)
            /** placing x axis points */
            xValues.forEachIndexed { i, value ->
                drawContext.canvas.nativeCanvas.drawText(
                    "$value",
                    xAxisSpace * (i + 1),
                    startPoint.y,
                    Paint().apply {
                        textSize = 12.sp.toPx()
                    }
                )
            }
            /** placing y axis points */
            yValues.forEachIndexed { i, value ->
                drawContext.canvas.nativeCanvas.drawText(
                    "$value",
                    startPoint.x,
                    size.height - yAxisSpace * (i + 1),
                    Paint().apply {
                        textSize = 12.sp.toPx()
                    }
                )
                drawLine(
                    color = numberLineColor,
                    start = Offset(0f + 60f, size.height - yAxisSpace * (i + 1)),
                    end = Offset(size.width + 40f, size.height - yAxisSpace * (i + 1)),
                    strokeWidth = 2f
                )
            }

            /** placing points */
            var currentPoint: Pair<Float, Float>? = null
            points.forEachIndexed { i, value ->
                val x1 = xAxisSpace * (i + 1)
                val y1 = size.height - (yAxisSpace * (value - (yValues.min() - 1)))
                drawCircle(
                    color = pointColor,
                    radius = 10f,
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

fun DrawScope.drawCoordinateSystem(color: Color, startPointF: PointF) {
    val lineSize = 6f
    val xAxeOffset = 70f
    val yAxeOffset = 60f
    drawLine(
        color = color,
        start = Offset(startPointF.x + yAxeOffset, size.height - xAxeOffset),
        end = Offset(0f + yAxeOffset, 0f - 40f),
        strokeWidth = lineSize
    )
    drawLine(
        color = color,
        start = Offset(0f + yAxeOffset, size.height - xAxeOffset),
        end = Offset(size.width + 40f, size.height - xAxeOffset),
        strokeWidth = lineSize
    )
}
