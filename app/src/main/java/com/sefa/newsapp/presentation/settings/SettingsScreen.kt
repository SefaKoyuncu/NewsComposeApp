package com.sefa.newsapp.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sefa.newsapp.presentation.theme.blue
import com.sefa.newsapp.presentation.theme.lightBlue
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun SettingsScreen() {

    var showDialog by remember { mutableStateOf(false) } // Dialog'u göster/gizle durumu

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top, // Elemanları üste hizala
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Başlık bölümü
        Text(
            text = "Settings",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.displayMedium
        )

        Spacer(modifier = Modifier.weight(1f)) // "Settings" ile buton arasına boşluk ekle

        // Ortada "Log out" butonu
        Button(
            onClick = {},
            modifier = Modifier
                .width(IntrinsicSize.Max), // Buton genişliği, içindeki yazıdan biraz fazla
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(12.dp) // Köşeleri 12dp yuvarlak
        ) {
            Text(text = "Log out", color = Color.White)
        }

        TextButton(
            onClick = {
                showDialog = true
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 13.dp)
        ) {
            Text(
                text = "Change Password",
                color = Color.Black,
                style = MaterialTheme.typography.labelLarge)
        }

        Spacer(modifier = Modifier.weight(1f)) // Ekranın geri kalanını boş bırak

        // NYTNews
        Text(
            text = "NYTNews",
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        )
    }

    // AlertDialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = "Change Password ",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black
                )

                    },
            text = {
                Text(
                    text = "A link to change your password has been sent to your email address.",
                    color = Color.DarkGray,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    ) },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = "OK",
                        color = Color.Black,
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold,
                        )
                }
            },
            containerColor = Color.LightGray, // AlertDialog arka plan rengi
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}
