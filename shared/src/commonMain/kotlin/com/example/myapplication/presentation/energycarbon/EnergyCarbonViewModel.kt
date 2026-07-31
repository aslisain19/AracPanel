package com.example.myapplication.presentation.energycarbon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.EnergyPeriod
import com.example.myapplication.domain.model.EnergySource
import com.example.myapplication.domain.usecase.GetEnergyReadingsUseCase
import com.example.myapplication.domain.usecase.GetMaintenanceImpactUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EnergyCarbonViewModel(
    private val getEnergyReadingsUseCase: GetEnergyReadingsUseCase,
    private val getMaintenanceImpactUseCase: GetMaintenanceImpactUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EnergyCarbonUiState())
    val state: StateFlow<EnergyCarbonUiState> = _state.asStateFlow()

    init { load() }

    fun onPeriodChange(period: EnergyPeriod) {
        _state.update { it.copy(period = period) }
        load()
    }

    fun onSourceChange(source: EnergySource) {
        _state.update { it.copy(source = source) }
        load()
    }

    fun load() {
        val current = _state.value
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val readingsResult = getEnergyReadingsUseCase(current.period, current.source)
            val impactResult = getMaintenanceImpactUseCase()

            readingsResult
                .onSuccess { readings ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            readings = readings,
                            summary = readings.toSummary(),
                            maintenanceImpact = impactResult.getOrNull()
                        )
                    }
                }
                .onFailure { e ->
                    _state.update { it.copy(isLoading = false, error = e.message ?: "Veriler yüklenemedi") }
                }
        }
    }
}