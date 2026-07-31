package com.example.myapplication.presentation.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.usecase.DeleteReportUseCase
import com.example.myapplication.domain.usecase.GetReportsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReportListViewModel(
    private val getReportsUseCase: GetReportsUseCase,
    private val deleteReportUseCase: DeleteReportUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ReportListUiState())
    val state: StateFlow<ReportListUiState> = _state.asStateFlow()

    init { load() }

    fun load() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            getReportsUseCase()
                .onSuccess { items -> _state.update { it.copy(isLoading = false, allItems = items) } }
                .onFailure { e -> _state.update { it.copy(isLoading = false, error = e.message ?: "Raporlar yüklenemedi") } }
        }
    }

    fun onQueryChange(value: String) = _state.update { it.copy(query = value, page = 1) }
    fun goToNextPage() = _state.update { if (it.canGoNext) it.copy(page = it.page + 1) else it }
    fun goToPreviousPage() = _state.update { if (it.canGoPrevious) it.copy(page = it.page - 1) else it }
    fun goToFirstPage() = _state.update { it.copy(page = 1) }
    fun goToLastPage() = _state.update { it.copy(page = it.totalPages) }

    fun deleteReport(id: String) {
        viewModelScope.launch {
            deleteReportUseCase(id).onSuccess { s ->
                _state.update { it.copy(allItems = it.allItems.filterNot { r -> r.id == id }) }
            }
        }
    }
}