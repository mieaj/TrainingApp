package com.example.trainingapp.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.trainingapp.ui.TestScreen2
import com.example.trainingapp.ui.menu.MenuScreen

//https://medium.com/google-developer-experts/modular-navigation-with-jetpack-compose-fda9f6b2bef7
object NavigationDirections {
    val default  = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = ""
    }
    val menuScreen  = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "menu_screen"
    }
    val test  = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "test"
    }
}

fun NavGraphBuilder.screenNavigationGraph() {
    composable(NavigationDirections.menuScreen.destination) {
        MenuScreen()
    }
    composable(NavigationDirections.test.destination) {
        TestScreen2()
    }
}