package com.example.myapplication.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MaintenanceWorkOrderDto(
    val serialNumber: String,
    val durationRatio: Float,
    val planningDate: String,
    val state: String
)

@Serializable
data class DailyAlertCountDto(
    val date: String,
    val count: Int
)