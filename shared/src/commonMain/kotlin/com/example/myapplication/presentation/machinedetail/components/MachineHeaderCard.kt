package com.example.myapplication.presentation.machinedetail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.domain.model.Machine
import com.example.myapplication.ui.theme.AppTheme

@Composable
fun MachineHeaderCard(
    machine: Machine
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Box(
            modifier = Modifier.padding(24.dp)
        ) {
            Column {
                Text(
                    text = machine.name,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(12.dp))

                StatusBadge(machine.connectionStatus)

                Spacer(Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HeaderMetric(
                        icon = Icons.Default.Schedule,
                        title = AppTheme.strings.workingHoursLabel,
                        value = "${machine.workingHours}"
                    )

                    HeaderMetric(
                        icon = Icons.Default.TrendingUp,
                        title = AppTheme.strings.oeeLabel,
                        value = "%${machine.oee}"
                    )

                    HeaderMetric(
                        icon = Icons.Default.Build,
                        title = AppTheme.strings.lastMaintenanceLabel,
                        value = machine.lastMaintenance
                    )
                }
            }
        }
    }
}