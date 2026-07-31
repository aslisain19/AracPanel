package com.example.myapplication.data.mapper

import com.example.myapplication.data.dto.EnergyReadingDto
import com.example.myapplication.data.dto.MaintenanceImpactDto
import com.example.myapplication.domain.model.EnergyReading
import com.example.myapplication.domain.model.MaintenanceImpact
import com.example.myapplication.domain.model.TirePressureStatus

fun EnergyReadingDto.toDomain(): EnergyReading = EnergyReading(
    label = label,
    consumptionKwh = consumptionKwh,
    carbonKg = carbonKg
)

fun MaintenanceImpactDto.toDomain(): MaintenanceImpact = MaintenanceImpact(
    lastServiceDate = lastServiceDate,
    estimatedFuelSavingPercent = estimatedFuelSavingPercent,
    nextMaintenanceDate = nextMaintenanceDate,
    tirePressureStatus = TirePressureStatus.entries.first { it.apiValue == tirePressureStatus }
)