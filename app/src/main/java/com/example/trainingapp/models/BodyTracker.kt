package com.example.trainingapp.models

import androidx.compose.runtime.mutableStateListOf
import com.example.trainingapp.R

sealed class BodyTracker(
    val topic: String, val unit: String = "", var value: String, val icon: Int
) {
    companion object{
        const val HEIGHT = "Height"
        const val WEIGHT = "Weight"
        const val BMI = "BMI"
    }
    data class Height(val height: String) : BodyTracker(
        topic = HEIGHT,
        unit = "cm",
        value = height,
        icon = R.drawable.drawing_height
    )

    data class Weight(var weight: String) : BodyTracker(
        topic = WEIGHT,
        unit = "kg",
        value = weight,
        icon = R.drawable.weight
    )

    data class Bmi(val bmi: String) : BodyTracker(
        topic = BMI,
        value = bmi,
        icon = R.drawable.drawing_bmi
    )
}