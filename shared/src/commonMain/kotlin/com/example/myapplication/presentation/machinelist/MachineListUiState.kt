package com.example.myapplication.presentation.machinelist

import com.example.myapplication.domain.model.Machine

data class MachineListUiState(
    val isLoading: Boolean = false,
    val machines: List<Machine> = emptyList(),
    val error: String? = null
)