package com.example.myapplication.domain.model

data class MaintenanceWorkOrder(
    val serialNumber: String,
    val durationRatio: Float,
    val planningDate: String,
    val state: WorkOrderState
)

enum class WorkOrderState {
    PLANNED,
    IN_PROGRESS,
    COMPLETED
}

data class DailyAlertCount(
    val date: String,
    val count: Int
)