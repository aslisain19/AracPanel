package com.example.myapplication.data.mapper

import com.example.myapplication.data.dto.SensorReadingDto
import com.example.myapplication.domain.model.SensorReading

fun SensorReadingDto.toDomain(): SensorReading {
    return SensorReading(
        timestamp = this.timestamp,
        temperature = this.temperature,
        vibration = this.vibration,
        energy = this.energy
    )
}