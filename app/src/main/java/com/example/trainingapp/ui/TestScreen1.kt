package com.example.trainingapp.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.trainingapp.navigation.NavigationDirections
import com.example.trainingapp.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VM1 @Inject constructor(
    private val navigationManager: NavigationManager
): ViewModel(){

    fun navigate(){
        navigationManager.navigate(NavigationDirections.test)
    }
}

@Composable
fun TestScreen1(
    viewModel: VM1 = hiltViewModel()
) {
    /*...*/
    Button(onClick = { viewModel.navigate() }) {
        Text(text = "Click to navigate")
    }
}