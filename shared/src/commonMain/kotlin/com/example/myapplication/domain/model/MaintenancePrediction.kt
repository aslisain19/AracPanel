package com.example.myapplication.domain.model

data class MaintenancePrediction(
    val machineId: String,
    val remainingUsefulLifeDays: Int,
    val riskLevel: RiskLevel,
    val predictedFailureDate: String,
    val recommendation: String
)

enum class RiskLevel {
    HIGH,
    MEDIUM,
    LOW
}