package com.example.trainingapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.trainingapp.R
import com.example.trainingapp.ui.theme.LargePadding
import com.example.trainingapp.ui.theme.MediumPadding
import com.example.trainingapp.ui.theme.Shapes
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
        verticalArrangement = Arrangement.SpaceBetween,
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
                            if (HEIGHT == it.topic) return@OutlinedIconCard
                            selectedItem = it.topic
                        }
                    }
                }
                    item {
                        Spacer(modifier = Modifier.height(MediumPadding))
                        if (selectedItem== WEIGHT) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(SmallPadding),
                            ) {
                                var text by remember { mutableStateOf(TextFieldValue("")) }
                                OutlinedTextField(
                                    modifier = Modifier.weight(1f),
                                    value = text,
                                    maxLines = 1,
                                    shape = Shapes.medium,
                                    onValueChange = {
                                        text = it
                                    },
                                    label = { Text(text = "Update ${selectedItem}") },
                                    placeholder = { Text(text = "E.g: 10") },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                )
                                Button(
                                    modifier = Modifier, onClick = { },
                                    shape = Shapes.medium
                                ) {
                                    Text(maxLines = 1, text = "Update")
                                }
                            }
                        }
                    }

//        InfiniteCircularList(items = (1..10).toMutableList(), initialItem = 4)
            })
        if (selectedItem == HEIGHT||selectedItem.isEmpty()) return
        Graph(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}


