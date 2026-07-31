package com.example.myapplication.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SensorReadingDto(
    val timestamp: String,
    val temperature: Float,
    val vibration: Float,
    val energy: Float
)