package com.sefa.newsapp.presentation.main

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sefa.newsapp.presentation.components.NewsCard
import com.sefa.newsapp.presentation.components.Snackbar
import com.sefa.newsapp.presentation.theme.blue

@Composable
fun NewsScreen(navController: NavController, viewModel: NewsMainViewModel = hiltViewModel())
{
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(Unit) {
        viewModel.getNewsList()
    }

    LaunchedEffect(state.error) {
        if (state.error != "") {
            snackbarHostState.showSnackbar(state.error)
            viewModel.clearError()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = "News",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.displayMedium
        )

        Log.e("TAG",state.newsList.size.toString())

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator( color = blue )
                }
            }

            state.newsList.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(items = state.newsList, key = { item -> item.id ?: 0 }) { newsItem ->
                        NewsCard(newsItem = newsItem, navController = navController)
                    }
                }
            }
            state.error.isNotBlank() -> {
                Snackbar(snackbarHostState = snackbarHostState)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewsScreen() {
    val navController = rememberNavController()
    NewsScreen(navController)
}