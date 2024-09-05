package com.sefa.newsapp.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Black
    ) {
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "Home") },
            label = { if (currentRoute == "main") Text("Home") },
            selected = currentDestination?.route == "main",
            onClick = { navController.navigate("main") }
        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorites") },
            label = { if (currentRoute == "fav") Text("Favorites") },
            selected = currentDestination?.route == "fav",
            onClick = { navController.navigate("fav") }
        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Filled.Settings, contentDescription = "Settings") },
            label = { if (currentRoute == "settings") Text("Settings") },
            selected = currentDestination?.route == "settings",
            onClick = { navController.navigate("settings") }
        )
    }
}
