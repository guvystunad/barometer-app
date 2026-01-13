package com.example.baro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import com.example.baro.R


@Composable
fun SwitchButton(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val (destination, icon, description) = when (currentRoute) {
        "barometer" -> Triple(
            "altimeter",
            R.drawable.mountain,
            "Switch to Altimeter"
        )
        "altimeter" -> Triple(
            "barometer",
            R.drawable.barometer,
            "Switch to Barometer"
        )
        else -> Triple(
            "barometer",
            R.drawable.barometer,
            "Default switch"
        )
    }

    IconButton(
        onClick = {
            navController.navigate(destination) {
                // Avoid heavy backstack
                popUpTo("barometer") {saveState = true}
                launchSingleTop = true
                restoreState = true
            }
        },
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = description
        )
    }
}