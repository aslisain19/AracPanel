package com.example.myapplication.presentation.notifications

import com.example.myapplication.domain.model.Notification
import com.example.myapplication.domain.model.NotificationSeverity

data class NotificationUiState(
    val isLoading: Boolean = true,
    val allNotifications: List<Notification> = emptyList(),
    val filteredNotifications: List<Notification> = emptyList(),
    val selectedSeverity: NotificationSeverity? = null,
    val error: String? = null
)