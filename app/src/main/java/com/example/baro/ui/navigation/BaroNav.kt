package com.example.baro.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baro.ui.screens.AltimeterScreen
import com.example.baro.ui.screens.BarometerScreen

@Composable
fun BaroNav() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "barometer"
    ) {
        composable("barometer") {
            BarometerScreen(navController)
        }
        composable("altimeter") {
            AltimeterScreen(navController)
        }
    }
}