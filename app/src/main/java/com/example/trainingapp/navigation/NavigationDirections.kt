package com.example.trainingapp.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.trainingapp.ui.TestScreen1
import com.example.trainingapp.ui.TestScreen2

object NavigationDirections {
    val default  = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = ""
    }
    val profile  = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "profile"
    }
    val test  = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "test"
    }
}

fun NavGraphBuilder.screenGraph() {
    composable(NavigationDirections.profile.destination) {
        TestScreen1()
    }
    composable(NavigationDirections.test.destination) {
        TestScreen2()
    }
}