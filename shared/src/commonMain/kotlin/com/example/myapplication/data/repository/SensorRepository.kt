package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.SensorReading

interface SensorRepository {
    suspend fun getSensorReadings(machineId: String): List<SensorReading>
}