package com.sefa.newsapp.presentation.details

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sefa.newsapp.data.model.News
import com.sefa.newsapp.R
import com.sefa.newsapp.presentation.theme.NewsComposeAppTheme

@Composable
fun NewsDetailsScreen(news: News?, navController: NavController)
{
    news ?: return
    var isFavorite by remember { mutableStateOf(false) } // Favori durumu için

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            // Arka plandaki görsel

            /*var mediaModelUrl = ""
            news.media[0].mediaMetadata[2].url?.let {
                mediaModelUrl = it
            }*/

            Log.e("TAG",news.toString())
            AsyncImage(
                model = "https://static01.nyt.com/images/2024/08/12/multimedia/12GWEN-WALZ-cjqf/12GWEN-WALZ-cjqf-mediumThreeByTwo440-v2.jpg",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.news_image), // Varsayılan resim
                error = painterResource(id = R.drawable.error_image) // Hata resmi
            )

            // Üst kısımda geri butonu ve başlık
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black),
                                startY = 130f,
                                endY = 0f // Gradient'in uzunluğunu ayarlayabilirsiniz
                            )
                        )
                        .padding(8.dp)
                ){
                    // Geri butonu ve başlık
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f)) // Bu, metni alt ortalamak için gerekli alanı oluşturur

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black),
                                startY = 0f,
                                endY = 130f // Gradient'in uzunluğunu ayarlayabilirsiniz
                            )
                        )
                        .padding(8.dp)
                ) {
                    // Alt kısımda metin

                    var title = ""
                    title =
                        if (!news.title.isNullOrEmpty())
                            news.title!!
                        else
                            ""

                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(8.dp)
                    )
                }
            }
        }

        // Yazar Bilgisi ve İçerik
        Column(
            modifier = Modifier.padding(12.dp))
        {
            Row(
                modifier = Modifier.fillMaxWidth(), // Row tüm genişliği kaplayacak
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Card en solda, sabit genişlikte olacak
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.Black),
                ) {

                    val sectionText = when {
                        news.section.isNullOrEmpty() && news.subsection.isNullOrEmpty() -> ""
                        news.section.isNullOrEmpty() -> news.subsection.orEmpty()
                        news.subsection.isNullOrEmpty() -> news.section.orEmpty()
                        else -> "${news.section} (${news.subsection})"
                    }

                    Text(
                        text = sectionText,
                        color = Color.White,
                        modifier = Modifier.padding(10.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                Card(
                    modifier = Modifier.weight(1f)
                ) {}

                // IconButton en sağda
                IconButton(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier.align(Alignment.CenterVertically) // IconButton ortada hizalanmış olacak
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(33.dp)
                    )
                }
            }

            // Icon ve Text'i yanyana sola hizala
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                            .padding(end = 8.dp)
                    )

                    var byline = ""
                    byline =
                        if (!news.byline.isNullOrEmpty())
                            news.byline!!
                        else
                            ""
                    Text(
                        text = byline,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            Spacer(modifier = Modifier.height(8.dp))

            var date = ""
            date =
                if (!news.publishedDate.isNullOrEmpty())
                    news.publishedDate!!
                else
                    ""

            Text(
                text = date,
                color = Color.Black,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal
            )


            Spacer(modifier = Modifier.height(20.dp))

            var abstract = ""
            abstract =
                if (!news.abstract.isNullOrEmpty())
                    news.abstract!!
                else
                    ""

            Text(
                text = abstract,
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}