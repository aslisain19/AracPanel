package com.example.myapplication.data.repository

import com.example.myapplication.data.mapper.toDomain
import com.example.myapplication.data.remote.EnergyApi
import com.example.myapplication.domain.model.EnergyPeriod
import com.example.myapplication.domain.model.EnergyReading
import com.example.myapplication.domain.model.EnergySource
import com.example.myapplication.domain.model.MaintenanceImpact
import com.example.myapplication.domain.repository.EnergyCarbonRepository

class EnergyCarbonRepositoryImpl(private val api: EnergyApi) : EnergyCarbonRepository {

    override suspend fun getReadings(
        period: EnergyPeriod,
        source: EnergySource
    ): Result<List<EnergyReading>> = runCatching {
        api.getReadings(period.apiValue, source.apiValue).map { it.toDomain() }
    }

    override suspend fun getMaintenanceImpact(): Result<MaintenanceImpact> = runCatching {
        api.getMaintenanceImpact().toDomain()
    }
}