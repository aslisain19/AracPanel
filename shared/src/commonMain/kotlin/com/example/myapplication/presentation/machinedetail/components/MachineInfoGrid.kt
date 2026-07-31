package com.example.myapplication.presentation.machinedetail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.model.ConnectionStatus
import com.example.myapplication.domain.model.Machine
import com.example.myapplication.ui.theme.AppTheme

@Composable
fun MachineInfoGrid(
    machine: Machine
) {

    val status =
        if (machine.connectionStatus == ConnectionStatus.ONLINE)
            AppTheme.strings.onlineStatusLabel
        else
            AppTheme.strings.offlineStatusLabel

    Column {

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            MachineInfoItem(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.LocationOn,
                title = AppTheme.strings.locationLabel,
                value = machine.location
            )

            MachineInfoItem(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Memory,
                title = AppTheme.strings.modelLabel,
                value = machine.model
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            MachineInfoItem(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Numbers,
                title = AppTheme.strings.serialNumberLabel,
                value = machine.serialNumber
            )

            MachineInfoItem(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Settings,
                title = AppTheme.strings.statusLabel,
                value = status
            )
        }
    }
}