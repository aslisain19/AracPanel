package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Notification
import com.example.myapplication.domain.model.NotificationSeverity

class PrioritizeNotificationsUseCase {

    operator fun invoke(notifications: List<Notification>): List<Notification> {
        return notifications.sortedWith(
            compareBy(
                { it.isAcknowledged },
                { severityOrder(it.severity) }
            )
        )
    }

    private fun severityOrder(severity: NotificationSeverity): Int {
        return when (severity) {
            NotificationSeverity.CRITICAL -> 0
            NotificationSeverity.WARNING -> 1
            NotificationSeverity.INFO -> 2
        }
    }
}