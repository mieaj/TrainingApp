package com.example.trainingapp.ui.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trainingapp.ui.theme.MediumPadding
import com.example.trainingapp.ui.theme.components.BigButton


@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    viewModel: MenuViewModel = hiltViewModel()
){

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
    ){
        viewModel.navigate(it)
    }
}
@Composable
fun MenuScreen(
    list: List<String>,
    onClick: (String)->Unit
) = Column(modifier = Modifier.padding(MediumPadding)) {
    /*...*/
    for (i in list){
        BigButton(
            text = i,
            onClick = { onClick(i) }
        )
    }
}