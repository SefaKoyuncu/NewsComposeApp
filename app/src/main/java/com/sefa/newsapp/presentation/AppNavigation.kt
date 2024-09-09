package com.sefa.newsapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sefa.newsapp.domain.model.NewsUIModel
import com.sefa.newsapp.presentation.details.NewsDetailsScreen
import com.sefa.newsapp.presentation.fav.FavScreen
import com.sefa.newsapp.presentation.auth.forgot.ForgotPasswordScreen
import com.sefa.newsapp.presentation.auth.AuthViewModel
import com.sefa.newsapp.presentation.auth.login.LoginScreen
import com.sefa.newsapp.presentation.main.NewsScreen
import com.sefa.newsapp.presentation.auth.settings.SettingsScreen
import com.sefa.newsapp.presentation.auth.signup.SignUpScreen
import kotlinx.serialization.json.Json

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val authViewModel: AuthViewModel = hiltViewModel()
    val state = authViewModel.state.value

    val shouldShowBottomBar = currentRoute !in listOf("login", "signup", "forgot", "details/{newsJson}")

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {
                BottomNavigationBar(navController)
            }
        }
    )
    {paddingValues ->
    NavHost(navController = navController,
        startDestination = if (state.isLoading) "splash" else if (state.isLoggedIn) "main" else "login",
        modifier = Modifier.padding(paddingValues) ) {
            composable("login") { LoginScreen(navController) }
            composable("signup") { SignUpScreen(navController) }
            composable("forgot") { ForgotPasswordScreen(navController) }
            composable("main") { NewsScreen(navController) }
            composable(
                route = "details/{newsJson}",
                arguments = listOf(navArgument("newsJson") { type = NavType.StringType })
            ) { backStackEntry ->
                val newsJson = backStackEntry.arguments?.getString("newsJson")
                val news = newsJson?.let { Json.decodeFromString(NewsUIModel.serializer(), it) }
                news?.let { NewsDetailsScreen(it, navController) }
            }
            composable("settings") { SettingsScreen(navController) }
            composable("fav") { FavScreen(navController) }
            composable("splash") {SplashScreen()}
        }
    }
}
