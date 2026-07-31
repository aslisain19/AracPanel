package com.example.myapplication.domain.model

data class SensorReading(
    val timestamp: String,
    val temperature: Float,
    val vibration: Float,
    val energy: Float
)