package com.sefa.newsapp.presentation.auth.forgot

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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sefa.newsapp.presentation.auth.AuthViewModel
import com.sefa.newsapp.presentation.auth.login.isValidEmail
import com.sefa.newsapp.presentation.components.Snackbar
import com.sefa.newsapp.utils.isNetworkAvailable

@Composable
fun ForgotPasswordScreen(navController: NavController, authViewModel: AuthViewModel = hiltViewModel())
{
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val authState by authViewModel.state

    val snackbarHostState = remember { SnackbarHostState() }
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }


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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Forgot Password",
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
            label = {
                Text(
                    text ="Email",
                    style = MaterialTheme.typography.labelLarge
                )},
            isError = emailError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = if (emailError) 0.dp else 16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        if (emailError)
        {
            Text(
                text = "Enter a valid email.",
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }

        Button(
            onClick = {
                if (!context.isNetworkAvailable())
                {
                    snackbarMessage = "No internet connection. Please check your connection."
                    showSnackbar = true
                }
                else if (!isValidEmail(email))
                {
                    snackbarMessage = "Enter a valid email."
                    showSnackbar = true
                }
                else
                {
                    authViewModel.sendPasswordResetEmail(email)
                }            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Reset Password",
                style = MaterialTheme.typography.headlineSmall
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

    Snackbar(snackbarHostState = snackbarHostState)
}