package com.example.trainingapp.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.trainingapp.ui.exercise.ExerciseScreen
import com.example.trainingapp.ui.menu.MenuScreen

//https://medium.com/google-developer-experts/modular-navigation-with-jetpack-compose-fda9f6b2bef7
object NavigationDirections {
    val default  = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = ""
        override fun navigationRoute(arg: String): String {
            TODO("Not yet implemented")
        }
    }
    val menuScreen  = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "menu_screen"
        override fun navigationRoute(arg: String): String {
            TODO("Not yet implemented")
        }
    }
    val exercise  = object : NavigationCommand {
        val route: String = "exercise"
        val muscleGroupArg = "muscleGroup"
        override val arguments = listOf(navArgument(muscleGroupArg) { type = NavType.StringType })
        override val destination = "$route/{$muscleGroupArg}"
        override fun navigationRoute(arg: String) = "$route/$arg"
    }
}

fun NavGraphBuilder.screenNavigationGraph() {
    composable(NavigationDirections.menuScreen.destination) {
        MenuScreen()
    }
    composable(NavigationDirections.exercise.destination) {
        ExerciseScreen(arg = it.arguments?.getString("muscleGroup")?: "")
    }
}