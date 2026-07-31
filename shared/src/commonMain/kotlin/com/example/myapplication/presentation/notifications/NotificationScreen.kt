package com.example.myapplication.presentation.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.model.NotificationSeverity
import com.example.myapplication.ui.components.EmptyView
import com.example.myapplication.ui.components.ErrorView
import com.example.myapplication.ui.components.LoadingView
import com.example.myapplication.ui.theme.AppTheme
import com.example.myapplication.ui.theme.OnlineGreen
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment

@Composable
fun NotificationScreen(
    viewModel: NotificationViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(AppTheme.strings.notificationsTitle) })
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = uiState.selectedSeverity == null,
                    onClick = { viewModel.onSeveritySelected(null) },
                    label = { Text(AppTheme.strings.filterAll) }
                )
                FilterChip(
                    selected = uiState.selectedSeverity == NotificationSeverity.CRITICAL,
                    onClick = { viewModel.onSeveritySelected(NotificationSeverity.CRITICAL) },
                    label = { Text(AppTheme.strings.filterCritical) }
                )
                FilterChip(
                    selected = uiState.selectedSeverity == NotificationSeverity.WARNING,
                    onClick = { viewModel.onSeveritySelected(NotificationSeverity.WARNING) },
                    label = { Text(AppTheme.strings.filterWarning) }
                )
                FilterChip(
                    selected = uiState.selectedSeverity == NotificationSeverity.INFO,
                    onClick = { viewModel.onSeveritySelected(NotificationSeverity.INFO) },
                    label = { Text(AppTheme.strings.filterInfo) }
                )
            }

            when {
                uiState.isLoading -> LoadingView()
                uiState.error != null -> ErrorView(message = uiState.error ?: "Bilinmeyen hata")
                uiState.filteredNotifications.isEmpty() -> EmptyView(message = AppTheme.strings.noNotificationsMessage)
                else -> {
                    uiState.filteredNotifications.forEach { notification ->
                        val severityColor = when (notification.severity) {
                            NotificationSeverity.CRITICAL -> Color(0xFFEF4444)
                            NotificationSeverity.WARNING -> Color(0xFFF59E0B)
                            NotificationSeverity.INFO -> MaterialTheme.colorScheme.primary
                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = notification.errorCode, color = MaterialTheme.colorScheme.onSurface)
                                    Text(text = "×${notification.count}", color = severityColor)
                                }
                                Text(text = AppTheme.strings.alarmMessageFor(notification.errorCode), color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text(text = notification.timestamp, color = MaterialTheme.colorScheme.onSurfaceVariant)

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    if (notification.isAcknowledged) {
                                        Text(text = "✓ ${AppTheme.strings.acknowledgedLabel}", color = OnlineGreen)
                                    } else {
                                        TextButton(onClick = { viewModel.onAcknowledge(notification.id) }) {
                                            Text(AppTheme.strings.acknowledgeButton)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}