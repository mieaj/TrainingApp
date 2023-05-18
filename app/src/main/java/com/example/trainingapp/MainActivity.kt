package com.example.trainingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.trainingapp.navigation.NavigationDirections
import com.example.trainingapp.navigation.NavigationManager
import com.example.trainingapp.navigation.screenNavigationGraph
import com.example.trainingapp.ui.theme.MediumPadding
import com.example.trainingapp.ui.theme.TrainingAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TrainingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(MediumPadding),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HandleNavigation(navigationManager = navigationManager)
                }
            }
        }
    }
}

@Composable
fun HandleNavigation(navigationManager: NavigationManager) {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = NavigationDirections.menuScreen.destination
    ) {
        screenNavigationGraph()
    }

    navigationManager.commands.collectAsState().value.also { command ->
        if (command.destination.isNotEmpty()) {
            navController.navigate(command.destination)
        }
    }
}