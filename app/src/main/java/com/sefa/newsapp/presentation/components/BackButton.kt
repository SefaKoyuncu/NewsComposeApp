package com.sefa.newsapp.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.sefa.newsapp.R

@Composable
fun BackButton(navController: NavController, color: Color, modifier: Modifier)
{
    IconButton(onClick = {
        navController.popBackStack()
    })
    {
        Icon(
            painterResource(id = R.drawable.arrow_back),
            contentDescription = "Back",
            tint = color
        )
    }
}