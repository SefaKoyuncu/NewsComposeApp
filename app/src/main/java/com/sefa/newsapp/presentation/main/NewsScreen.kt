package com.sefa.newsapp.presentation.main

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.sefa.newsapp.domain.model.NewsUIModel
import com.sefa.newsapp.presentation.fav.NewsCard
import com.sefa.newsapp.presentation.theme.blue
import kotlinx.serialization.json.Json

@Composable
fun NewsScreen(navController: NavController, viewModel: NewsMainViewModel = hiltViewModel())
{
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        viewModel.getNewsList()
    }

    Column(modifier = Modifier.fillMaxSize()) {

        // Başlık bölümü
        Text(
            text = "News",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.displayMedium
        )

        Log.e("TAG",state.movieList.size.toString())

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator( color = blue )
                }
                Log.e("TAG","Loading")
            }

            state.error.isNotBlank() -> {
                //ErrorText(text = state.error)
                Log.e("TAG","error")

            }

            state.movieList.isNotEmpty() -> {
                Log.e("TAG","success")

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.movieList) { newsItem ->
                        NewsCard(newsItem = newsItem, navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun NewsCard(newsItem: NewsUIModel, navController: NavController) {
    Card(
        onClick = {
            val newsJson = Json.encodeToString(NewsUIModel.serializer(), newsItem)
            navController.navigate("details/${Uri.encode(newsJson)}")
        },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, Color.Black), // Siyah çerçeve ekler
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White), // Kart içi beyaz olacak
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = newsItem.imageUrl ?: "",
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(2.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {

                val sectionText = when {
                    newsItem.section.isNullOrEmpty() && newsItem.subsection.isNullOrEmpty() -> ""
                    newsItem.section.isNullOrEmpty() -> newsItem.subsection.orEmpty()
                    newsItem.subsection.isNullOrEmpty() -> newsItem.section.orEmpty()
                    else -> "${newsItem.section} (${newsItem.subsection})"
                }

                Text(
                    text = sectionText,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                newsItem.title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                newsItem.publishedDate?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewsScreen() {
    // Sahte bir NavController ile NewsScreen'i çağırıyoruz
    val navController = rememberNavController()
    NewsScreen(navController)
}