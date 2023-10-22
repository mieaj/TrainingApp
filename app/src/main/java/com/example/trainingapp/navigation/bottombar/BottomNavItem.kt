package com.example.trainingapp.navigation.bottombar

import com.example.trainingapp.R
import com.example.trainingapp.navigation.NavigationDirections

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
    var destination: String
) {
    object Body :
        BottomNavItem(
            title = "Body",
            icon = R.drawable.ic_body,
            destination = NavigationDirections.menuScreen.destination
        )

    object Weight :
        BottomNavItem(
            title = "Weight",
            icon = R.drawable.ic_dumbbell,
            destination = NavigationDirections.menuScreen.destination
        )

    object More :
        BottomNavItem(
            title = "More",
            icon = R.drawable.ic_more,
            destination = NavigationDirections.menuScreen.destination
        )
}