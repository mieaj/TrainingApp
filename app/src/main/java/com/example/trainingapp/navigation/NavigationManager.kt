package com.example.trainingapp.navigation

import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {

    var commands = MutableStateFlow("" to false)

    fun navigate(
        directions: String,
        popBackStack: Boolean = false
    ) {
        commands.value = directions to popBackStack
    }

}