package com.sefa.newsapp.presentation.components

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sefa.newsapp.domain.model.NewsUIModel
import kotlinx.serialization.json.Json

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
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
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