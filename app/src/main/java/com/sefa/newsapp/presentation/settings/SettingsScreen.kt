package com.sefa.newsapp.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top, // Elemanları üste hizala
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Üstteki "Settings" texti
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(100.dp)) // "Settings" ile buton arasına boşluk ekle

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

        // Buton ve ClickableText arasındaki boşluğu minimuma indirdik
        TextButton(
            onClick = {}, // Navigate to SignUpScreen
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

        // En altta ortalanmış text
        Text(
            text = "NYTNews",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}
