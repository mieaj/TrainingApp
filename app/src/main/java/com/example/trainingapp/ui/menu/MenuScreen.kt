package com.example.trainingapp.ui.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingapp.R
import com.example.trainingapp.ui.theme.MediumPadding
import com.example.trainingapp.ui.theme.components.IconButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    viewModel: MenuViewModel = hiltViewModel()
) {

    val listTest = listOf(
//        "Workout days",
//        "View all days",
//        "View exercises",
//        "Track you progress",
//        "Body tracking"
        "Leg",
        "Biceps",
        "Triceps",
        "Back",
        "Core",
        "Should"
    )
    MenuScreen(
        list = listTest
    ) {
    }
}

@Composable
fun MenuScreen(
    list: List<String>,
    onClick: (String) -> Unit
) = Column(
    modifier = Modifier.padding(MediumPadding).fillMaxSize(),
    verticalArrangement = Arrangement.SpaceEvenly
) {
//    /*...*/
//    for (i in list){
//        BigButton(
//            text = i,
//            onClick = { onClick(i) }
//        )
//    }
//    }
    IconButton(icon = R.drawable.leg_drawing, text = "Test1") {}
    IconButton(modifier = Modifier, icon = R.drawable.ic_leg, text = "Test1") {}
    IconButton(modifier = Modifier, icon = R.drawable.drawing_arm, text = "Test1") {}
    IconButton(modifier = Modifier, icon = R.drawable.ic_arm, text = "Test1") {}
    IconButton(modifier = Modifier, icon = R.drawable.ic_body, text = "Test1") {}


    IconButton(
        modifier = Modifier,
        icon = R.drawable.drawing_upper_body1,
        text = "Test1"
    ) {}


    IconButton(
        modifier = Modifier,
        icon = R.drawable.ic_body_back,
        text = "Test1"
    ) {}

}