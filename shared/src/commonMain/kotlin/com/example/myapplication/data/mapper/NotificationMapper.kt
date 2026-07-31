package com.example.myapplication.data.mapper

import com.example.myapplication.data.dto.NotificationDto
import com.example.myapplication.domain.model.Notification
import com.example.myapplication.domain.model.NotificationSeverity

fun NotificationDto.toDomain(): Notification {
    return Notification(
        id = this.id,
        machineId = this.machineId,
        errorCode = this.errorCode,
        message = this.message,
        severity = when (this.severity) {
            "CRITICAL" -> NotificationSeverity.CRITICAL
            "WARNING" -> NotificationSeverity.WARNING
            else -> NotificationSeverity.INFO
        },
        count = this.count,
        timestamp = this.timestamp
    )
}