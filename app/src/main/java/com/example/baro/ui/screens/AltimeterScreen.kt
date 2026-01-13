package com.example.baro.ui.screens

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baro.ui.components.RefreshButton
import com.example.baro.ui.components.SwitchButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.baro.R
import androidx.compose.foundation.layout.size
import kotlin.math.pow
import java.util.Locale
@Composable
fun AltimeterScreen(navController: NavHostController) {
    val context = LocalContext.current

    var currentAltitude by remember { mutableStateOf(0f) }
    var isReading by remember { mutableStateOf(false) }

    val seaLevelPressure = 1013.25f

    fun refreshSensorReading() {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)

        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    if (it.sensor.type == Sensor.TYPE_PRESSURE) {
                        val pressureHPa = it.values[0]
                        val altitudeMeters = 44330 * (1 - (pressureHPa / seaLevelPressure).toDouble().pow(1.0 / 5.255))
                        currentAltitude = altitudeMeters.toFloat()
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
            Text(text = "Altimeter", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Icon(painter = painterResource(
                id = R.drawable.mountain),
                contentDescription = "mountain",
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
                text = "meters",
                fontSize = 48.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF4A5DB8)
            )
            Text(
                text = if (currentAltitude != 0f || isReading) {
                    String.format(Locale.US, "%.0f", currentAltitude)
                } else {
                    "---"
                },
                fontSize = 72.sp,
                fontWeight = FontWeight.Light,
                color = Color(0xFF4A5DB8)
            )
            Text(
                text = "above sea level",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        }
    }
}