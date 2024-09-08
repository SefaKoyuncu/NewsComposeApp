package com.sefa.newsapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sefa.newsapp.domain.model.NewsUIModel
import com.sefa.newsapp.presentation.details.NewsDetailsScreen
import com.sefa.newsapp.presentation.fav.FavScreen
import com.sefa.newsapp.presentation.forgot.ForgotPasswordScreen
import com.sefa.newsapp.presentation.login.LoginScreen
import com.sefa.newsapp.presentation.main.NewsScreen
import com.sefa.newsapp.presentation.settings.SettingsScreen
import com.sefa.newsapp.presentation.signup.SignUpScreen
import kotlinx.serialization.json.Json

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Mevcut rotayı al
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Bu rotalarda BottomNavigationBar'ı göstermeyin
    val shouldShowBottomBar = currentRoute !in listOf("login", "signup", "forgot", "details/{newsJson}")


    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {
                BottomNavigationBar(navController)
            }
        }
    )
    {paddingValues ->
    NavHost(navController = navController, startDestination = "login", modifier = Modifier.padding(paddingValues) ) {
            composable("login") { LoginScreen(navController) }
            composable("signup") { SignUpScreen(navController) }
            composable("forgot") { ForgotPasswordScreen() }
            composable("main") { NewsScreen(navController) }
            composable(
                route = "details/{newsJson}",
                arguments = listOf(navArgument("newsJson") { type = NavType.StringType })
            ) { backStackEntry ->
                val newsJson = backStackEntry.arguments?.getString("newsJson")
                val news = newsJson?.let { Json.decodeFromString(NewsUIModel.serializer(), it) }
                news?.let { NewsDetailsScreen(it, navController) }
            }
            composable("settings") { SettingsScreen() }
            composable("fav") { FavScreen(navController) }
        }
    }

}
