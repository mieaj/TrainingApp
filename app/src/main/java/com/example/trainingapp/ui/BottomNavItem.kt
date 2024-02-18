package com.example.trainingapp.ui

import com.example.trainingapp.R
import com.example.trainingapp.ui.destinations.BodyTrackerScreenDestination
import com.example.trainingapp.ui.destinations.DirectionDestination
import com.example.trainingapp.ui.destinations.MenuScreenDestination
import com.example.trainingapp.ui.destinations.MoreScreenDestination

internal enum class BottomNavItem(
    var title: String,
    var icon: Int,
    var destination: DirectionDestination
) {
    Body(
        title = "Body",
        icon = R.drawable.ic_body,
        destination = BodyTrackerScreenDestination
    ),
    Training(
        title = "Training",
        icon = R.drawable.ic_dumbbell,
        destination = MenuScreenDestination
    ),
    More(
        title = "More",
        icon = R.drawable.ic_more,
        destination = MoreScreenDestination
    )
}