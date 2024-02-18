package com.example.trainingapp.ui.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingapp.R
import com.example.trainingapp.ui.destinations.ExerciseScreenDestination
import com.example.trainingapp.ui.theme.MediumPadding
import com.example.trainingapp.ui.theme.SmallPadding
import com.example.trainingapp.ui.theme.components.IconCard
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    viewModel: MenuViewModel = hiltViewModel()
) {
//        "Workout days",
//        "View all days",
//        "View exercises",
//        "Track you progress",
//        "Body tracking"
    val listTest = listOf(
        "Leg" to R.drawable.leg_drawing,
        "Arms" to R.drawable.drawing_arm,
        "Back & Shoulders" to R.drawable.drawing_back,
        "Core" to R.drawable.drawing_upper_body2,

        )
    MenuScreen(
        list = listTest
    ) {
        navigator.navigate(ExerciseScreenDestination(it))
    }
}

@Composable
fun MenuScreen(
    list: List<Pair<String, Int>>,
    onClick: (String) -> Unit
) = LazyVerticalStaggeredGrid(
    modifier = Modifier.padding(MediumPadding),
    columns = StaggeredGridCells.Fixed(2),
    verticalItemSpacing = SmallPadding,
    horizontalArrangement = Arrangement.spacedBy(SmallPadding),
    content = {
        for (i in list) {
            val (text, icon) = i
            item {
                IconCard(
                    icon = icon,
                    onClick = { onClick(text) }
                )
            }
        }
    })

