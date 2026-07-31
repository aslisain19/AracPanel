package com.example.myapplication.presentation.machinedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.repository.MachineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MachineDetailViewModel(
    private val machineId: String,
    private val repository: MachineRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MachineDetailUiState())
    val uiState: StateFlow<MachineDetailUiState> = _uiState.asStateFlow()

    init {
        loadMachine()
    }

    private fun loadMachine() {
        viewModelScope.launch {
            try {
                val machines = repository.getMachines()
                val machine = machines.find { it.serialNumber == machineId }
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    machine = machine
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