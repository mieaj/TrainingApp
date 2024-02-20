package com.example.trainingapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.trainingapp.models.BodyTracker
import com.example.trainingapp.models.BodyTracker.Companion.HEIGHT
import com.example.trainingapp.models.BodyTracker.Companion.WEIGHT
import com.example.trainingapp.ui.theme.MediumPadding
import com.example.trainingapp.ui.theme.Shapes
import com.example.trainingapp.ui.theme.SmallPadding
import com.example.trainingapp.ui.theme.components.Graph
import com.example.trainingapp.ui.theme.components.HeightDialog
import com.example.trainingapp.ui.theme.components.OutlinedIconCard
import com.ramcosta.composedestinations.annotation.Destination
import kotlin.math.ceil

@Destination
@Composable
fun BodyTrackerScreen() {
    var height by remember { mutableStateOf<Int?>(null) }
    val weight = remember {
        mutableStateListOf<Float>(
//            64f, 63.4f, 62f
        )
    }
    val bmi = remember {
        mutableStateListOf<Float>(
//            20f, 20.4f, 22f
        )
    }

    val items = mutableStateListOf(
        BodyTracker.Height(height?.toString() ?: ""),
        BodyTracker.Weight(weight.lastOrNull()?.toString() ?: ""),
        BodyTracker.Bmi(bmi.lastOrNull()?.toString() ?: ""),
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
        var showDialog by remember { mutableStateOf(false) }
        HeightDialog(showDialog = showDialog,
            currentValue = height ?: 150,
            onDismissRequest = { showDialog = false }) {
            showDialog = false
            height = it
            weight.lastOrNull()?.let { weight ->
                bmi.add(ceil(weight / (it / 50)))
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding)
        ) {
            ShowMeasurements(items, selectedItem) {
                if (HEIGHT == it) {
                    showDialog = true
                } else {
                    selectedItem = it
                }
            }
            when (selectedItem) {
                HEIGHT, "" -> return
                WEIGHT -> {
                    UpdateWeight {
                        weight.add(it.toFloat())
                        height?.let { height ->
                            bmi.add(ceil(weight.last() / (height / 50)))
                        }
                    }
                }
            }
        }
        Graph(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = SmallPadding)
                .height(200.dp),
            points = if (selectedItem == WEIGHT) weight else bmi
        )
    }
}

@Composable
fun ShowMeasurements(
    items: List<BodyTracker>, selectedItem: String, onClick: (String) -> Unit
) = LazyColumn(
    modifier = Modifier, verticalArrangement = Arrangement.spacedBy(SmallPadding)
) {
    items.forEach {
        item {
            OutlinedIconCard(
                modifier = Modifier,
                icon = it.icon,
                topic = "${it.topic}:",
                value = it.value,
                unit = it.unit,
                selected = it.topic == selectedItem
            ) {
                onClick(it.topic)
            }
        }
    }
}

@Composable
fun UpdateWeight(onUpdate: (String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SmallPadding),
    ) {
        var text by remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = text,
            maxLines = 1,
            shape = Shapes.medium,
            onValueChange = {
                text = it.replace(",", ".")
            },
            label = { Text(text = "Update $WEIGHT") },
            placeholder = { Text(text = "E.g: 10") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done, keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(onDone = {
                onUpdate(text)
                text = ""
                keyboardController?.hide()
            })
        )
        Button(
            modifier = Modifier, onClick = {
                onUpdate(text)
                text = ""
                keyboardController?.hide()
            }, shape = Shapes.medium
        ) {
            Text(maxLines = 1, text = "Update")
        }
    }
}
