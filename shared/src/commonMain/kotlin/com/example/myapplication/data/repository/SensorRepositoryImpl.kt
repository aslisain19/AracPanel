package com.example.myapplication.data.repository

import com.example.myapplication.data.mapper.toDomain
import com.example.myapplication.data.remote.SensorApi
import com.example.myapplication.domain.model.SensorReading
import com.example.myapplication.domain.repository.SensorRepository

class SensorRepositoryImpl(
    private val api: SensorApi
) : SensorRepository {

    override suspend fun getSensorReadings(machineId: String): List<SensorReading> {
        return api.getSensorReadings(machineId).map { it.toDomain() }
    }
}