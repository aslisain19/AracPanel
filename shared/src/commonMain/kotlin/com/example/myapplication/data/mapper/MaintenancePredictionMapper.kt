package com.example.myapplication.data.mapper

import com.example.myapplication.data.dto.MaintenancePredictionDto
import com.example.myapplication.domain.model.MaintenancePrediction
import com.example.myapplication.domain.model.RiskLevel

fun MaintenancePredictionDto.toDomain(): MaintenancePrediction {
    return MaintenancePrediction(
        machineId = this.machineId,
        remainingUsefulLifeDays = this.remainingUsefulLifeDays,
        riskLevel = when (this.riskLevel) {
            "HIGH" -> RiskLevel.HIGH
            "MEDIUM" -> RiskLevel.MEDIUM
            else -> RiskLevel.LOW
        },
        predictedFailureDate = this.predictedFailureDate,
        recommendation = this.recommendation
    )
}