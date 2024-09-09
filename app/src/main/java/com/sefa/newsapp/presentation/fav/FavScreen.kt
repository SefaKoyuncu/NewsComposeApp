package com.sefa.newsapp.presentation.fav

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sefa.newsapp.presentation.components.NewsCard
import com.sefa.newsapp.presentation.components.Snackbar
import com.sefa.newsapp.presentation.theme.blue

@Composable
fun FavScreen(navController: NavController, viewModel: FavViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(Unit) {
        viewModel.getFavList()
    }

    LaunchedEffect(state.error) {
        if (state.error != "") {
            snackbarHostState.showSnackbar(state.error)
            viewModel.clearError()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Favorites News",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.displayMedium
        )

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = blue)
                }
            }

            state.error.isNotBlank() -> {
                Snackbar(snackbarHostState = snackbarHostState)
            }

            state.favList.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                )
                {
                    items(state.favList) { newsItem ->
                        NewsCard(newsItem = newsItem, navController = navController)
                    }
                }
            }
        }
    }
}