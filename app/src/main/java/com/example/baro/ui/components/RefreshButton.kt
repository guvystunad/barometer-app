package com.example.baro.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.example.baro.R

@Composable
fun RefreshButton(
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isRotating by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (isRotating) 360f else 0f,
        animationSpec = tween(durationMillis = 500),
        finishedListener = { isRotating = false },
        label = "refresh_rotation"
    )

    IconButton(
        onClick = {
            isRotating = true
            onRefresh()
        },
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.reload),
            contentDescription = "Refresh the reading",
            modifier = Modifier.rotate(rotation)
        )
    }
}