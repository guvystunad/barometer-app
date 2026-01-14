package com.example.baro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class UnitSystem {
    EU, US
}

@Composable
fun UnitPicker(
    selectedUnit: UnitSystem,
    onUnitChange: (UnitSystem) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "EU",
            fontSize = 18.sp,
            fontWeight = if (selectedUnit == UnitSystem.EU) FontWeight.Bold else FontWeight.Normal,
            color = if (selectedUnit == UnitSystem.EU) Color.Black else Color.Gray,
            modifier = Modifier
                .clickable { onUnitChange(UnitSystem.EU)}
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Text(
            text = "|",
            fontSize = 18.sp,
            color = Color.Gray
        )
        Text(
            text = "US",
            fontSize = 18.sp,
            fontWeight = if (selectedUnit == UnitSystem.US) FontWeight.Bold else FontWeight.Normal,
            color = if (selectedUnit == UnitSystem.US) Color.Black else Color.Gray,
            modifier = Modifier
                .clickable { onUnitChange(UnitSystem.US)}
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}