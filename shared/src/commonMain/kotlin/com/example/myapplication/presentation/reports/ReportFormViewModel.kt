package com.example.myapplication.presentation.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.*
import com.example.myapplication.domain.usecase.CreateReportUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface ReportFormEvent {
    data object Submitted : ReportFormEvent
    data class SubmitFailed(val message: String) : ReportFormEvent
}

class ReportFormViewModel(
    private val createReportUseCase: CreateReportUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ReportFormUiState())
    val state: StateFlow<ReportFormUiState> = _state.asStateFlow()

    private val _events = MutableStateFlow<ReportFormEvent?>(null)
    val events: StateFlow<ReportFormEvent?> = _events.asStateFlow()

    fun onNameChange(value: String) = _state.update { it.copy(name = value, errorFields = it.errorFields - ReportFormField.NAME) }
    fun onGroupByXChange(value: GroupByOption) = _state.update { it.copy(groupByX = value, errorFields = it.errorFields - ReportFormField.GROUP_BY_X) }
    fun onGroupByYChange(value: GroupByOption) = _state.update { it.copy(groupByY = value, errorFields = it.errorFields - ReportFormField.GROUP_BY_Y) }
    fun onDisplayValueChange(value: DisplayValue) = _state.update { it.copy(displayValue = value, errorFields = it.errorFields - ReportFormField.DISPLAY_VALUE) }
    fun onDateRangeChange(value: DateRangeKind) = _state.update { it.copy(dateRangeKind = value, errorFields = it.errorFields - ReportFormField.DATE_RANGE) }
    fun onFrequencyChange(value: ReportFrequency) = _state.update { it.copy(frequency = value) }
    fun onScheduledTimeChange(value: ScheduledTime) = _state.update { it.copy(scheduledTime = value, errorFields = it.errorFields - ReportFormField.SCHEDULED_TIME) }
    fun onToggleScheduled(value: Boolean) = _state.update { it.copy(isScheduled = value, scheduledTime = if (value) it.scheduledTime else null) }
    fun onToggleSentEmail(value: Boolean) = _state.update { it.copy(sentEmail = value) }
    fun onToggleSentSms(value: Boolean) = _state.update { it.copy(sentSms = value) }
    fun onToggleSentNotification(value: Boolean) = _state.update { it.copy(sentNotification = value) }
    fun consumeEvent() = _events.update { null }

    fun submit() {
        val current = _state.value
        val validationErrors = current.validate()
        if (validationErrors.isNotEmpty()) {
            _state.update { it.copy(errorFields = validationErrors) }
            return
        }
        val command = current.toCommandOrNull() ?: return

        viewModelScope.launch {
            _state.update { it.copy(isSubmitting = true) }
            val result = createReportUseCase(command)
            _state.update { it.copy(isSubmitting = false) }
            result.onSuccess { _events.update { ReportFormEvent.Submitted } }
                .onFailure { e -> _events.update { ReportFormEvent.SubmitFailed(e.message ?: "unknown") } }
        }
    }
}