package com.example.myapplication.presentation.sensorchart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.repository.SensorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SensorChartViewModel(
    private val machineId: String,
    private val repository: SensorRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SensorChartUiState())
    val uiState: StateFlow<SensorChartUiState> = _uiState.asStateFlow()

    init {
        loadReadings()
    }

    private fun loadReadings() {
        viewModelScope.launch {
            try {
                val readings = repository.getSensorReadings(machineId)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    readings = readings
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}