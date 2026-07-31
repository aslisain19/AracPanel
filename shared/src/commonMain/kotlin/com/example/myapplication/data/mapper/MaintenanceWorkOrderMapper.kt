package com.example.myapplication.data.mapper

import com.example.myapplication.data.dto.DailyAlertCountDto
import com.example.myapplication.data.dto.MaintenanceWorkOrderDto
import com.example.myapplication.domain.model.DailyAlertCount
import com.example.myapplication.domain.model.MaintenanceWorkOrder
import com.example.myapplication.domain.model.WorkOrderState

fun MaintenanceWorkOrderDto.toDomain(): MaintenanceWorkOrder {
    return MaintenanceWorkOrder(
        serialNumber = this.serialNumber,
        durationRatio = this.durationRatio,
        planningDate = this.planningDate,
        state = when (this.state) {
            "PLANNED" -> WorkOrderState.PLANNED
            "IN_PROGRESS" -> WorkOrderState.IN_PROGRESS
            else -> WorkOrderState.COMPLETED
        }
    )
}

fun DailyAlertCountDto.toDomain(): DailyAlertCount {
    return DailyAlertCount(
        date = this.date,
        count = this.count
    )
}