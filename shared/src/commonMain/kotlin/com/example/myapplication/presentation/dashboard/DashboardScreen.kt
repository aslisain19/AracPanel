package com.example.myapplication.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.AppTheme
import com.example.myapplication.ui.theme.OfflineRed
import com.example.myapplication.ui.theme.OnlineGreen


@Composable
fun DashboardScreen(
    onNavigateToMachines: () -> Unit,
    onNavigateToWelds: () -> Unit,
    onNavigateToMaintenance: () -> Unit,
    onNavigateToReports: () -> Unit,
    onNavigateToEnergyCarbon: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(AppTheme.strings.dashboardTitle) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = AppTheme.strings.welcomeMessage,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    DashboardCard(
                        title = AppTheme.strings.activeMachinesLabel,
                        value = "1 / 5",
                        statusColor = OnlineGreen
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    DashboardCard(
                        title = AppTheme.strings.activeAlarmsLabel,
                        value = "268",
                        statusColor = OfflineRed
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    DashboardCard(
                        title = AppTheme.strings.todayWeldDurationLabel,
                        value = "1.07 ${AppTheme.strings.hoursUnit}",
                        statusColor = MaterialTheme.colorScheme.primary
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    DashboardCard(
                        title = AppTheme.strings.totalWeldRecordsLabel,
                        value = "142",
                        statusColor = MaterialTheme.colorScheme.tertiary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = AppTheme.strings.quickAccessTitle,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Button(
                onClick = onNavigateToMachines,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(AppTheme.strings.machineListButton)
            }

            Button(
                onClick = onNavigateToWelds,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(AppTheme.strings.weldHistoryButton)
            }

            Button(
                onClick = onNavigateToMaintenance,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(AppTheme.strings.maintenanceButton)
            }

            Button(
                onClick = onNavigateToReports,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(AppTheme.strings.reportsButton)
            }

            Button(
                onClick = onNavigateToEnergyCarbon,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = OnlineGreen),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(AppTheme.strings.energyCarbonButton)
            }

            Button(
                onClick = onNavigateToSettings,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(AppTheme.strings.settingsButton)
            }
        }
    }
}

@Composable
fun DashboardCard(
    title: String,
    value: String,
    statusColor: Color
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(1f)
                )

                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(color = statusColor, shape = RoundedCornerShape(5.dp))
                )
            }
            Text(
                text = value,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}