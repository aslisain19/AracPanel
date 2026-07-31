package com.example.myapplication.domain.model

data class Notification(
    val id: String,
    val machineId: String,
    val errorCode: String,
    val message: String,
    val severity: NotificationSeverity,
    val count: Int,
    val timestamp: String,
    val isAcknowledged: Boolean = false
)

enum class NotificationSeverity {
    CRITICAL,
    WARNING,
    INFO
}