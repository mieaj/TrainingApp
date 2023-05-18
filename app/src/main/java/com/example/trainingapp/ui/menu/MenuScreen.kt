package com.example.trainingapp.ui.menu

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MenuScreen(
    viewModel: MenuViewModel = hiltViewModel()
) {
    /*...*/
    Button(onClick = { viewModel.navigate() }) {
        Text(text = "Click to navigate")
    }
}