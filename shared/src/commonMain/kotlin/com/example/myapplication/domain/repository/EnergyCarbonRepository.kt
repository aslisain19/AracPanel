package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.EnergyPeriod
import com.example.myapplication.domain.model.EnergyReading
import com.example.myapplication.domain.model.EnergySource
import com.example.myapplication.domain.model.MaintenanceImpact

interface EnergyCarbonRepository {
    suspend fun getReadings(period: EnergyPeriod, source: EnergySource): Result<List<EnergyReading>>
    suspend fun getMaintenanceImpact(): Result<MaintenanceImpact>
}