package com.example.myapplication.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MaintenancePredictionDto(
    val machineId: String,
    val remainingUsefulLifeDays: Int,
    val riskLevel: String,
    val predictedFailureDate: String,
    val recommendation: String
)