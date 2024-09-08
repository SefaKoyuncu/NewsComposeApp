package com.sefa.newsapp.presentation

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sefa.newsapp.R
import com.sefa.newsapp.presentation.theme.blue

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Black,
        tonalElevation = NavigationBarDefaults.Elevation,
        modifier = Modifier.height(76.dp)

    ) {
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "Home") },
            label = {
                if (currentRoute == "main")
                Text(text = "Home",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.labelMedium) },
            selected = currentDestination?.route == "main",
            onClick = { navController.navigate("main") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White, // Seçili öğenin simge rengi
                unselectedIconColor = Color.Gray, // Seçili olmayan öğenin simge rengi
                selectedTextColor = blue, // Seçili öğenin yazı rengi
                unselectedTextColor = Color.Gray, // Seçili olmayan öğenin yazı rengi
                indicatorColor = blue // Seçili öğe arka plan rengi
            )
        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorites") },
            label = { if (currentRoute == "fav") Text("Favorites") },
            selected = currentDestination?.route == "fav",
            onClick = { navController.navigate("fav") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White, // Seçili öğenin simge rengi
                unselectedIconColor = Color.Gray, // Seçili olmayan öğenin simge rengi
                selectedTextColor = blue, // Seçili öğenin yazı rengi
                unselectedTextColor = Color.Gray, // Seçili olmayan öğenin yazı rengi
                indicatorColor = blue // Seçili öğe arka plan rengi
            )
        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Filled.Settings, contentDescription = "Settings") },
            label = { if (currentRoute == "settings") Text("Settings") },
            selected = currentDestination?.route == "settings",
            onClick = { navController.navigate("settings") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White, // Seçili öğenin simge rengi
                unselectedIconColor = Color.Gray, // Seçili olmayan öğenin simge rengi
                selectedTextColor = blue, // Seçili öğenin yazı rengi
                unselectedTextColor = Color.Gray, // Seçili olmayan öğenin yazı rengi
                indicatorColor = blue // Seçili öğe arka plan rengi
            )
        )
    }
}
