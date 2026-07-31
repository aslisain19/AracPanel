package com.example.myapplication.presentation.energycarbon

import com.example.myapplication.domain.model.EnergyCarbonSummary
import com.example.myapplication.domain.model.EnergyPeriod
import com.example.myapplication.domain.model.EnergyReading
import com.example.myapplication.domain.model.EnergySource
import com.example.myapplication.domain.model.MaintenanceImpact

data class EnergyCarbonUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val period: EnergyPeriod = EnergyPeriod.WEEKLY,
    val source: EnergySource = EnergySource.ALL,
    val readings: List<EnergyReading> = emptyList(),
    val summary: EnergyCarbonSummary? = null,
    val maintenanceImpact: MaintenanceImpact? = null
)

fun List<EnergyReading>.toSummary(): EnergyCarbonSummary {
    val totalKwh = sumOf { it.consumptionKwh }
    val totalCarbon = sumOf { it.carbonKg }
    val changePercent = -8.4
    val trees = (totalCarbon / 21.0).toInt()
    return EnergyCarbonSummary(
        totalConsumptionKwh = totalKwh,
        totalCarbonKg = totalCarbon,
        changeVsPreviousPercent = changePercent,
        equivalentTreesPlanted = trees
    )
}