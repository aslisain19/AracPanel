package com.example.myapplication.presentation.machinelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.repository.MachineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.myapplication.domain.usecase.GetMachineListUseCase
import com.example.myapplication.domain.usecase.RetryUseCase

class MachineListViewModel(
    private val repository: MachineRepository,
    private val getMachineListUseCase: GetMachineListUseCase,
    private val retryUseCase: RetryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MachineListUiState())
    val uiState: StateFlow<MachineListUiState> = _uiState.asStateFlow()

    init {
        loadMachine()  // ViewModel oluşturulur oluşturulmaz, veriyi çekmeye başla
    }      //Java'daki constructor'ın içine kod yazmak gibi

    private fun loadMachine() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            // mevcut state'in bir kopyasını alıp, sadece belirttiğin alanı değiştiriyorsun, diğerleri aynı kalıyor
            try {
                val machines = retryUseCase(maxAttempts = 3) {
                    repository.getMachines()
                }
                val sortedMachines = getMachineListUseCase(machines)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    machines = sortedMachines
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




