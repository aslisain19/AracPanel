package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Notification
import com.example.myapplication.domain.model.NotificationSeverity

class FilterNotificationsUseCase {

    operator fun invoke(
        notifications: List<Notification>,
        severity: NotificationSeverity?
    ): List<Notification> {
        return if (severity == null) {
            notifications
        } else {
            notifications.filter { it.severity == severity }
        }
    }
}