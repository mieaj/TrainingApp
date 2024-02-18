package com.example.trainingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.trainingapp.ui.BottomNavItem
import com.example.trainingapp.ui.BottomNavigation
import com.example.trainingapp.ui.NavGraphs
import com.example.trainingapp.ui.destinations.MenuScreenDestination
import com.example.trainingapp.ui.theme.TrainingAppTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import com.ramcosta.composedestinations.utils.startDestination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val currentDestination =
                navController.currentDestinationAsState().value ?: NavGraphs.root.startDestination
            TrainingAppTheme {
                Scaffold(
                    bottomBar = {
                        if (currentDestination in BottomNavItem.entries.map { it.destination }) {
                            BottomNavigation(
                                navigator = navController,
                                currentDestination = currentDestination
                            )
                        }
                    },
                ) {
                    DestinationsNavHost(
                        modifier = Modifier.padding(it),
                        navController = navController,
                        navGraph = NavGraphs.root,
                        engine = rememberAnimatedNavHostEngine()
                    )
                }
            }
        }
    }
}