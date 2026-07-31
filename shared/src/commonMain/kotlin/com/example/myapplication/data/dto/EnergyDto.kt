package com.example.myapplication.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EnergyReadingDto(
    val label: String,
    val consumptionKwh: Double,
    val carbonKg: Double
)

@Serializable
data class MaintenanceImpactDto(
    val lastServiceDate: String,
    val estimatedFuelSavingPercent: Double,
    val nextMaintenanceDate: String,
    val tirePressureStatus: String
)