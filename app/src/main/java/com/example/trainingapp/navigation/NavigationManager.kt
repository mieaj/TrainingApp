package com.example.trainingapp.navigation

import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {

    var commands = MutableStateFlow("")

    fun navigate(
        directions: String
    ) {
        commands.value = directions
    }

}