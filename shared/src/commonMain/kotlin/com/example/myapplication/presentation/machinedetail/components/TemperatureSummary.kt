package com.example.myapplication.presentation.machinedetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeviceThermostat
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TemperatureSummary() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        MetricCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.KeyboardArrowUp,
            title = "Max",
            value = "54°C"
        )

        MetricCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.DeviceThermostat,
            title = "Avg",
            value = "47°C"
        )

        MetricCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.KeyboardArrowDown,
            title = "Min",
            value = "42°C"
        )

        MetricCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.ShowChart,
            title = "Trend",
            value = "+4°C"
        )
    }
}