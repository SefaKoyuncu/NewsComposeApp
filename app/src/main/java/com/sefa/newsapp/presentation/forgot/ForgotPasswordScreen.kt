package com.sefa.newsapp.presentation.forgot

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sefa.newsapp.presentation.theme.NewsComposeAppTheme


@Composable
fun ForgotPasswordScreen() {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // İçeriği ortalamak için Spacer
        Spacer(modifier = Modifier.weight(1f))

        // Title
        Text(
            text = "Forgot Password",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp),
            style = MaterialTheme.typography.displayMedium
        )

        // Email TextField
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = !com.sefa.newsapp.presentation.login.isValidEmail(email)
            },
            label = { Text(text ="Email", style = MaterialTheme.typography.labelLarge) },
            isError = emailError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = if (emailError) 0.dp else 16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        if (emailError) {
            Text(
                text = "Enter a valid email.",
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }

        // Login Button
        Button(
            onClick = {
                if (!com.sefa.newsapp.presentation.login.isValidEmail(email)) {
                    Toast.makeText(context, "Enter a valid email.", Toast.LENGTH_SHORT).show()
                }
                else {
                    // Login Logic
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Reset Password", style = MaterialTheme.typography.headlineSmall)
        }

        // İçeriği ortalamak için Spacer
        Spacer(modifier = Modifier.weight(1f))

        // NYTNews
        Text(
            text = "NYTNews",
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    NewsComposeAppTheme {
        ForgotPasswordScreen()
    }
}

