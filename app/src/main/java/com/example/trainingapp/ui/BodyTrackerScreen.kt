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
import com.example.trainingapp.ui.theme.MediumPadding
import com.example.trainingapp.ui.theme.SmallPadding
import com.example.trainingapp.ui.theme.components.Graph
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


