package com.example.trainingapp.ui.menu

import androidx.lifecycle.ViewModel
import com.example.trainingapp.navigation.NavigationDirections
import com.example.trainingapp.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val navigationManager: NavigationManager
): ViewModel(){

    fun navigate(muscleGroup: String){
        navigationManager.navigate(NavigationDirections.exercise.navigationRoute(muscleGroup))
    }
}