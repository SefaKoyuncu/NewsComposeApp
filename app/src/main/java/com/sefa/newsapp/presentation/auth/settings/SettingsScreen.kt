package com.sefa.newsapp.presentation.auth.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sefa.newsapp.presentation.auth.AuthViewModel
import com.sefa.newsapp.presentation.components.Snackbar

@Composable
fun SettingsScreen(navController: NavController, authViewModel: AuthViewModel = hiltViewModel()) {

    val authState by authViewModel.state
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(authState.error) {
        if (authState.error != null) {
            snackbarHostState.showSnackbar(authState.error ?: "Oops, an error occured!")
            authViewModel.clearError()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                authViewModel.logout()
                navController.navigate("login") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            },
            modifier = Modifier
                .width(IntrinsicSize.Max),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Log out",
                color = Color.White)
        }

        TextButton(
            onClick = {
                authViewModel.sendResetEmailToCurrentUser()
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

        Spacer(modifier = Modifier.weight(1f))

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

    Snackbar(snackbarHostState = snackbarHostState)
}