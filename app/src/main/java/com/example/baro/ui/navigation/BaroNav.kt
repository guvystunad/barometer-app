package com.example.baro.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baro.ui.components.UnitSystem
import com.example.baro.ui.screens.AltimeterScreen
import com.example.baro.ui.screens.BarometerScreen

@Composable
fun BaroNav() {
    val navController = rememberNavController()
    var selectedUnit by remember { mutableStateOf(UnitSystem.EU) }

    NavHost(
        navController = navController,
        startDestination = "barometer"
    ) {
        composable("barometer") {
            BarometerScreen(
                navController = navController,
                selectedUnit = selectedUnit,
                onUnitChange = { selectedUnit = it }
            )
        }
        composable("altimeter") {
            AltimeterScreen(
                navController = navController,
                selectedUnit = selectedUnit,
                onUnitChange = { selectedUnit = it }
            )
        }
    }
}