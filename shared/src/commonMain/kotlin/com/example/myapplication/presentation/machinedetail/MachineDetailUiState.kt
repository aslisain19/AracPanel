package com.example.myapplication.presentation.machinedetail

import com.example.myapplication.domain.model.Machine

data class MachineDetailUiState(
    val isLoading: Boolean = true,
    val machine: Machine? = null,
    val error: String? = null
)