package com.example.myapplication.presentation.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.NotificationSeverity
import com.example.myapplication.domain.repository.NotificationRepository
import com.example.myapplication.domain.usecase.FilterNotificationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.myapplication.domain.usecase.PrioritizeNotificationsUseCase
class NotificationViewModel(
    private val repository: NotificationRepository,
    private val filterUseCase: FilterNotificationsUseCase,
    private val prioritizeUseCase: PrioritizeNotificationsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationUiState())
    val uiState: StateFlow<NotificationUiState> = _uiState.asStateFlow()

    init {
        loadNotifications()
    }

    private fun loadNotifications() {
        viewModelScope.launch {
            try {
                val notifications = prioritizeUseCase(repository.getNotifications())
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    allNotifications = notifications,
                    filteredNotifications = notifications
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun onSeveritySelected(severity: NotificationSeverity?) {
        val filtered = filterUseCase(_uiState.value.allNotifications, severity)
        _uiState.value = _uiState.value.copy(
            selectedSeverity = severity,
            filteredNotifications = filtered
        )
    }

    fun onAcknowledge(notificationId: String) {
        val updatedAll = _uiState.value.allNotifications.map { notification ->
            if (notification.id == notificationId) {
                notification.copy(isAcknowledged = true)
            } else {
                notification
            }
        }
        val prioritized = prioritizeUseCase(updatedAll)
        val filtered = filterUseCase(prioritized, _uiState.value.selectedSeverity)
        _uiState.value = _uiState.value.copy(
            allNotifications = prioritized,
            filteredNotifications = filtered
        )
    }
}