package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.EnergyPeriod
import com.example.myapplication.domain.model.EnergyReading
import com.example.myapplication.domain.model.EnergySource
import com.example.myapplication.domain.model.MaintenanceImpact
import com.example.myapplication.domain.repository.EnergyCarbonRepository

class GetEnergyReadingsUseCase(private val repository: EnergyCarbonRepository) {
    suspend operator fun invoke(period: EnergyPeriod, source: EnergySource): Result<List<EnergyReading>> =
        repository.getReadings(period, source)
}

class GetMaintenanceImpactUseCase(private val repository: EnergyCarbonRepository) {
    suspend operator fun invoke(): Result<MaintenanceImpact> = repository.getMaintenanceImpact()
}