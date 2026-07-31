package com.example.myapplication.presentation.sensorchart

import com.example.myapplication.domain.model.SensorReading

data class SensorChartUiState(
    val isLoading: Boolean = true,
    val readings: List<SensorReading> = emptyList(),
    val error: String? = null
)