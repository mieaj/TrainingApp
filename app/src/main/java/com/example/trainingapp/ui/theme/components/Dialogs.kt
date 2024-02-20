package com.example.trainingapp.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.trainingapp.ui.theme.ExtraSmallPadding
import com.example.trainingapp.ui.theme.MediumPadding
import com.example.trainingapp.ui.theme.Shapes
import com.example.trainingapp.ui.theme.SmallPadding

@Composable
fun HeightDialog(
    showDialog: Boolean,
    currentValue: Int,
    onDismissRequest: () -> Unit,
    onChange: (Int) -> Unit,
) {
    if (!showDialog) return
    Dialog(
        onDismissRequest = onDismissRequest, properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        var selected by remember {
            mutableStateOf(0)
        }
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Column(
                Modifier
                    .pointerInput(Unit) { detectTapGestures { } }
                    .shadow(SmallPadding, shape = Shapes.medium)
                    .width(300.dp)
                    .clip(Shapes.medium)
                    .background(MaterialTheme.colorScheme.surface),
                horizontalAlignment = Alignment.CenterHorizontally) {
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
                    TextButton(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                        colors = ButtonDefaults.buttonColors(),
                        shape = Shapes.medium,
                        onClick = {
                            onChange(selected)
                        }) {
                        Text(text = "Ok")
                    }
                }

            }
        }
    }
}