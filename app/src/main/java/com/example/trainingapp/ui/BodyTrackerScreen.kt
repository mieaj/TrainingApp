package com.example.trainingapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trainingapp.R
import com.example.trainingapp.ui.theme.LargePadding
import com.example.trainingapp.ui.theme.MediumPadding
import com.example.trainingapp.ui.theme.SmallPadding
import com.example.trainingapp.ui.theme.components.Graph
import com.example.trainingapp.ui.theme.components.OutlinedIconCard
import com.ramcosta.composedestinations.annotation.Destination


const val HEIGHT = "Height"
const val WEIGHT = "Weight"
const val BMI = "BMI"

sealed class BodyTracker(val topic: String, val unit: String ="", val value: String, val icon: Int) {
    data class Height(val height: String) : BodyTracker(topic = HEIGHT, unit = "cm", value = height, icon = R.drawable.drawing_height)
    data class Weight(val weight: String) : BodyTracker(topic = WEIGHT, unit = "kg", value = weight, icon = R.drawable.weight)
    data class Bmi(val bmi: String) : BodyTracker(topic = BMI, value = bmi, icon = R.drawable.drawing_bmi)
}

@Destination
@Composable
fun BodyTrackerScreen() {
    val items = listOf(
        BodyTracker.Height("22"),
        BodyTracker.Weight("22"),
        BodyTracker.Bmi("22"),
    )
    var selectedItem by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MediumPadding),
        verticalArrangement = Arrangement.spacedBy(LargePadding),
    ) {

        LazyVerticalStaggeredGrid(
            modifier = Modifier,
            columns = StaggeredGridCells.Fixed(1),
            verticalItemSpacing = SmallPadding,
            horizontalArrangement = Arrangement.spacedBy(SmallPadding),
            content = {
                items.forEach {
                    item {
                        OutlinedIconCard(
                            modifier = Modifier,
                            icon = it.icon,
                            topic = "${it.topic}:",
                            value = it.value,
                            selected = it.topic == selectedItem
                        ) {
                            selectedItem = it.topic
                        }
                    }
                }
            })
        Graph(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}


