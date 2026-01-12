package com.example.baro.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baro.ui.components.SwitchButton

@Composable
fun AltimeterScreen(navController: NavHostController) {

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        SwitchButton(navController = navController, modifier = Modifier.align(Alignment.TopStart))
        Text(
            text = "Altimeter",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 24.dp)
        )
    }
}