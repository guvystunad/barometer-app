package com.example.baro.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.baro.ui.components.SwitchButton
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.content.Context
import android.hardware.SensorManager
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorEvent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.baro.ui.components.RefreshButton
import com.example.baro.R
import java.util.Locale

@Composable
fun BarometerScreen(navController: NavHostController) {
    val context = LocalContext.current

    var currentPressure by remember { mutableStateOf(0f) }
    var isReading by remember { mutableStateOf(false) }

    fun refreshSensorReading() {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)

        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    if (it.sensor.type == Sensor.TYPE_PRESSURE) {
                        val pressureHPa = it.values[0]
                        currentPressure = pressureHPa * 0.02953f
                        isReading = false
                        sensorManager.unregisterListener(this)
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
        pressureSensor?.let {
            isReading = true
            sensorManager.registerListener(listener, it, SensorManager.SENSOR_DELAY_FASTEST)
        }
    }

    LaunchedEffect(Unit) {
        refreshSensorReading()
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SwitchButton(navController = navController)
            Text(text = "Barometer", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Icon(painter = painterResource(
                id = R.drawable.barometer),
                contentDescription = "barometer",
                modifier = Modifier.size(32.dp))
            RefreshButton(onRefresh = { refreshSensorReading() })
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "inHg",
                fontSize = 48.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF4A5DB8)
            )

            Text(
                text = if (currentPressure > 0f) {
                    String.format(Locale.US, "%.1f", currentPressure).replace(".", ",")
                } else {
                    if (isReading) "Reading..." else "---"
                },
                fontSize = 72.sp,
                fontWeight = FontWeight.Light,
                color = Color(0xFF4A5DB8)
            )
        }
    }
}