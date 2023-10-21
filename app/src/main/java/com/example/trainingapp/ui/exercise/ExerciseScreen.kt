package com.example.trainingapp.ui.exercise

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ExerciseScreen(arg: String){

    val legs = "legs"
    val biceps = "biceps"
    val elsed = "else"

    val listLeg = listOf(
        ""
    )

    val listBack = listOf(
        "Inverted rows",
        "Australian pull-ups",
        "Scapular shrugs",
        "Negative pull-ups",
        "Pull-ups",
        "Chin ups",
        "Wide grip pull-ups",
        "Archer pull-ups",
        "Typewriter pull-ups",
        "One-arm pull-ups",
        "Front lever"
    )
    val listChest = listOf(
        "Diamond push-ups",
        "Decline push-ups",
        "Ring/Bar dips",
        "Push-ups",
        "Archer push-ups",
        "One-arm push-ups",
        "Back lever"
    )
    val text = when(arg){
        "Leg" -> legs
        else -> elsed
    }
    Text(text = arg)
}