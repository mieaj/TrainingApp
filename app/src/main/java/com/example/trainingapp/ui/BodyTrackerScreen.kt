package com.example.trainingapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun BodyTrackerScreen() {
    Column {
        Text(text = "BodyTracker")
    }
}