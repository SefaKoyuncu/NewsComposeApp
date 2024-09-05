package com.sefa.newsapp.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.util.PatternsCompat
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Login",
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
                emailError = !isValidEmail(email)
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

        // Password TextField with visibility toggle
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = password.length < 8
            },
            label = { Text(text ="Password", style = MaterialTheme.typography.labelLarge) },
            isError = passwordError,
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible)
                    painterResource(id = com.sefa.newsapp.R.drawable.visibility_24px)
                else
                    painterResource(id = com.sefa.newsapp.R.drawable.visibility_off_24px)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = icon, contentDescription = null)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = if (passwordError) 0.dp else 16.dp),
            )

        if (passwordError) {
            Text(
                text = "Your password must be at least 8 characters.",
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }

        // Remember Me Checkbox
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it }
            )
            Text(
                text ="Remember Me",
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.weight(1f))

            // Forgot Password Button
            TextButton(
                onClick = { navController.navigate("forgot") }, // Navigate to SignUpScreen
            ) {
                Text(text = "Forgot Password", color = Color.Gray, style = MaterialTheme.typography.labelLarge)
            }
        }

        // Login Button
        Button(
            onClick = {
                if (!isValidEmail(email)) {
                    Toast.makeText(context, "Enter a valid email.", Toast.LENGTH_SHORT).show()
                } else if (passwordError) {
                    Toast.makeText(context, "Your password must be at least 8 characters.", Toast.LENGTH_SHORT).show()
                } else {
                    // Login Logic
                    navController.navigate("main")
                    //navController.popBackStack()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = "LOG IN", style = MaterialTheme.typography.headlineSmall)
        }

        // Sign Up Text
        TextButton(
            onClick = { navController.navigate("signup") }, // Navigate to SignUpScreen
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Sign Up",
                color = Color.Black,
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.labelLarge)
        }
    }
}

fun isValidEmail(email: String): Boolean {
    return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
}
