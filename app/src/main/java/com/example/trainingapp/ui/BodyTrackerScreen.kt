package com.example.trainingapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.trainingapp.R
import com.example.trainingapp.ui.theme.ExtraSmallPadding
import com.example.trainingapp.ui.theme.MediumPadding
import com.example.trainingapp.ui.theme.Shapes
import com.example.trainingapp.ui.theme.SmallPadding
import com.example.trainingapp.ui.theme.components.Graph
import com.example.trainingapp.ui.theme.components.InfiniteCircularList
import com.example.trainingapp.ui.theme.components.OutlinedIconCard
import com.ramcosta.composedestinations.annotation.Destination


const val HEIGHT = "Height"
const val WEIGHT = "Weight"
const val BMI = "BMI"

sealed class BodyTracker(
    val topic: String,
    val unit: String = "",
    var value: String,
    val icon: Int
) {
    data class Height(val height: String) :
        BodyTracker(topic = HEIGHT, unit = "cm", value = height, icon = R.drawable.drawing_height)

    data class Weight(var weight: String) :
        BodyTracker(topic = WEIGHT, unit = "kg", value = weight, icon = R.drawable.weight)

    data class Bmi(val bmi: String) :
        BodyTracker(topic = BMI, value = bmi, icon = R.drawable.drawing_bmi)
}

@Destination
@Composable
fun BodyTrackerScreen() {
    val items by remember {
        mutableStateOf(
            listOf(
                BodyTracker.Height("112"),
                BodyTracker.Weight("22"),
                BodyTracker.Bmi("22"),
            )
        )
    }
    var selectedItem by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MediumPadding),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        var showDialog by remember { mutableStateOf(false) }
        CustomDialog(
            showDialog = showDialog,
            currentValue = items[0].value.toInt(),
            onDismissRequest = { showDialog = false }) {
            showDialog = false
            items[0].value = it.toString()
        }
        ShowMeasurements(items, selectedItem) {
            if (HEIGHT == it) {
                showDialog = true
            } else {
                selectedItem = it
            }
        }
        if (selectedItem == HEIGHT || selectedItem.isEmpty()) return
        Graph(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }

}

@Composable
fun CustomDialog(
    showDialog: Boolean,
    currentValue: Int,
    onDismissRequest: () -> Unit,
    onChange: (Int) -> Unit,
) {
    if (!showDialog) return
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        var selected by remember {
            mutableStateOf(0)
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                Modifier
                    .pointerInput(Unit) { detectTapGestures { } }
                    .shadow(SmallPadding, shape = Shapes.medium)
                    .width(300.dp)
                    .clip(Shapes.medium)
                    .background(MaterialTheme.colorScheme.surface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InfiniteCircularList(
                    modifier = Modifier.padding(MediumPadding),
                    items = (100..200).toMutableList(),
                    initialItem = currentValue
                ) {
                    selected = it
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MediumPadding),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = Shapes.medium,
                        onClick = onDismissRequest
                    ) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(ExtraSmallPadding))
                    TextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(),
                        shape = Shapes.medium,
                        onClick = {
                            onChange(selected)
                        }
                    ) {
                        Text(text = "Ok")
                    }
                }

            }
        }
    }
}

@Composable
fun ShowMeasurements(
    items: List<BodyTracker>,
    selectedItem: String,
    onClick: (String) -> Unit
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
                        onClick(it.topic)
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(MediumPadding))
                if (selectedItem == WEIGHT) {
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

        })
}
