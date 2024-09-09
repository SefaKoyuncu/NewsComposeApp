package com.sefa.newsapp.presentation.auth.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sefa.newsapp.presentation.auth.AuthViewModel
import com.sefa.newsapp.presentation.components.Snackbar
import com.sefa.newsapp.utils.isNetworkAvailable

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel = hiltViewModel())
{
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    val authState by authViewModel.state

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        authViewModel.checkAuthState()
    }

    LaunchedEffect(authState.error) {
        if (authState.error != null) {
            snackbarHostState.showSnackbar(authState.error ?: "Oops, an error occured!")
            authViewModel.clearError()
        }
    }

    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            snackbarHostState.showSnackbar(snackbarMessage)
            showSnackbar = false
        }
    }

    if (authState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    else if (authState.isLoggedIn) {
        LaunchedEffect(Unit) {
            navController.navigate("main") {
                popUpTo("login") { inclusive = true }
            }
        }
    }
    else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Login",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp),
                style = MaterialTheme.typography.displayMedium
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = !isValidEmail(email)
                },
                label = { Text(text = "Email", style = MaterialTheme.typography.labelLarge) },
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

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = password.length < 8
                },
                label = { Text(text = "Password", style = MaterialTheme.typography.labelLarge) },
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
                    text = "Remember Me",
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.weight(1f))

                TextButton(
                    onClick = {
                        navController.navigate("forgot")},
                ) {
                    Text(
                        text = "Forgot Password",
                        color = Color.Gray,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Button(
                onClick = {
                    if (!context.isNetworkAvailable()) {
                        snackbarMessage = "No internet connection. Please check your connection."
                        showSnackbar = true
                    } else if (!isValidEmail(email)) {
                        snackbarMessage = "Enter a valid email."
                        showSnackbar = true
                    } else if (passwordError) {
                        snackbarMessage = "Your password must be at least 8 characters."
                        showSnackbar = true
                    } else {
                        authViewModel.login(email, password, rememberMe)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(text = "LOG IN", style = MaterialTheme.typography.headlineSmall)
            }

            TextButton(
                onClick = {
                    navController.navigate("signup")},
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Sign Up",
                    color = Color.Black,
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "NYTNews",
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }

    Snackbar(snackbarHostState = snackbarHostState)
}

fun isValidEmail(email: String): Boolean {
    return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
}
