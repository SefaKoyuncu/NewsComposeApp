package com.sefa.newsapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sefa.newsapp.data.model.News
import com.sefa.newsapp.presentation.details.NewsDetailsScreen
import com.sefa.newsapp.presentation.forgot.ForgotPasswordScreen
import com.sefa.newsapp.presentation.login.LoginScreen
import com.sefa.newsapp.presentation.main.NewsScreen
import com.sefa.newsapp.presentation.settings.SettingsScreen
import com.sefa.newsapp.presentation.signup.SignUpScreen
import kotlinx.serialization.json.Json

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("forgot") { ForgotPasswordScreen() }
        composable("main") { NewsScreen(navController) }
        composable(
            route = "details/{newsJson}",
            arguments = listOf(navArgument("newsJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val newsJson = backStackEntry.arguments?.getString("newsJson")
            val news = newsJson?.let { Json.decodeFromString(News.serializer(), it) }
            news?.let { NewsDetailsScreen(it, navController) }
        }
        composable("settings") { SettingsScreen() }
    }
}
