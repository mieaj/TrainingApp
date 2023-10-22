package com.example.trainingapp.navigation.bottombar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.example.trainingapp.navigation.NavigationManager

@Composable
fun BottomNavigation(navigationManager: NavigationManager) {

    val items = listOf(
        BottomNavItem.Body,
        BottomNavItem.Weight,
        BottomNavItem.More
    )
    var selectedScreen by remember { mutableStateOf(items.first()) }

    NavigationBar {
        items.forEach { item ->
            AddItem(
                screen = item,
                isSelected = selectedScreen == item
            ){
                navigationManager.navigate(it.destination, true)
                selectedScreen = it
            }
        }
    }
}
@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    isSelected: Boolean,
    onClick: (BottomNavItem) -> Unit
) {
    NavigationBarItem(
        // Text that shows bellow the icon
        label = {
            Text(text = screen.title)
        },

        // The icon resource
        icon = {
            Icon(
                painterResource(id = screen.icon),
                contentDescription = screen.title
            )
        },

        // Display if the icon it is select or not
        selected = isSelected,

        // Always show the label bellow the icon or not
        alwaysShowLabel = true,

        // Click listener for the icon
        onClick = { onClick(screen)},

        // Control all the colors of the icon
        colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.secondary)
    )
}