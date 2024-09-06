package com.sefa.newsapp.presentation.fav

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sefa.newsapp.data.model.Media
import com.sefa.newsapp.data.model.MediaMetadata
import com.sefa.newsapp.data.model.News
import com.sefa.newsapp.R
import kotlinx.serialization.json.Json

@Composable
fun FavScreen(navController: NavController) {
        val news = listOf(
            News(
                publishedDate = "2024-08-14",
                section = "Style",
                byline = "By Joseph Bernstein",
                title = "Gwen Walz, the Coolheaded, Ultracompetent Political Spouse",
                abstract = "Early in her husband’s political career, she stepped in to help. Some wondered: Why isn’t she running?",
                media = listOf(Media(mediaMetadata = arrayListOf(
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg"),
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg")
                )), Media(mediaMetadata = arrayListOf(
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg"),
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg")
                )))
            ),
            News(
                publishedDate = "2024-08-14",
                section = "Style",
                byline = "By Joseph Bernstein",
                title = "Gwen Walz, the Coolheaded, Ultracompetent Political Spouse",
                abstract = "Early in her husband’s political career, she stepped in to help. Some wondered: Why isn’t she running?",
                media = listOf(Media(mediaMetadata = arrayListOf(
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg"),
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg")
                )), Media(mediaMetadata = arrayListOf(
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg"),
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg")
                )))
            ),
            News(
                publishedDate = "2024-08-14",
                section = "Style",
                byline = "By Joseph Bernstein",
                title = "Gwen Walz, the Coolheaded, Ultracompetent Political Spouse",
                abstract = "Early in her husband’s political career, she stepped in to help. Some wondered: Why isn’t she running?",
                media = listOf(Media(mediaMetadata = arrayListOf(
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg"),
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg")
                )), Media(mediaMetadata = arrayListOf(
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg"),
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg")
                )))
            ),
            News(
                publishedDate = "2024-08-14",
                section = "Style",
                byline = "By Joseph Bernstein",
                title = "Gwen Walz, the Coolheaded, Ultracompetent Political Spouse",
                abstract = "Early in her husband’s political career, she stepped in to help. Some wondered: Why isn’t she running?",
                media = listOf(Media(mediaMetadata = arrayListOf(
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg"),
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg")
                )), Media(mediaMetadata = arrayListOf(
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg"),
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg")
                )))
            ),
            News(
                publishedDate = "2024-08-14",
                section = "Style",
                byline = "By Joseph Bernstein",
                title = "Gwen Walz, the Coolheaded, Ultracompetent Political Spouse",
                abstract = "Early in her husband’s political career, she stepped in to help. Some wondered: Why isn’t she running?",
                media = listOf(Media(mediaMetadata = arrayListOf(
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg"),
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg")
                )), Media(mediaMetadata = arrayListOf(
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg"),
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg")
                )))
            ),
            News(
                publishedDate = "2024-08-14",
                section = "Style",
                byline = "By Joseph Bernstein",
                title = "Gwen Walz, the Coolheaded, Ultracompetent Political Spouse",
                abstract = "Early in her husband’s political career, she stepped in to help. Some wondered: Why isn’t she running?",
                media = listOf(Media(mediaMetadata = arrayListOf(
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg"),
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg")
                )), Media(mediaMetadata = arrayListOf(
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg"),
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg")
                )))
            ),
            News(
                publishedDate = "2024-08-14",
                section = "Style",
                byline = "By Joseph Bernstein",
                title = "Gwen Walz, the Coolheaded, Ultracompetent Political Spouse",
                abstract = "Early in her husband’s political career, she stepped in to help. Some wondered: Why isn’t she running?",
                media = listOf(Media(mediaMetadata = arrayListOf(
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg"),
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg")
                )), Media(mediaMetadata = arrayListOf(
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg"),
                    MediaMetadata(url = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg")
                )))
            ),
        )

    Column(modifier = Modifier.fillMaxSize()) {
        // Başlık bölümü
        Text(
            text = "Favorites News",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.displayMedium
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()) {
            items(news) { newsItem ->
                NewsCard(newsItem = newsItem, navController = navController)
            }
        }
    }

      /*  LazyColumn(
            modifier = Modifier.fillMaxSize()) {
            items(news) { newsItem ->
                NewsCard(newsItem = newsItem, navController = navController)
            }
        }*/
}

@Composable
fun NewsCard(newsItem: News, navController: NavController) {
    Card(
        onClick = {
            val newsJson = Json.encodeToString(News.serializer(), newsItem)
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
                model = newsItem.media.firstOrNull()?.mediaMetadata?.firstOrNull()?.url ?: "",
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