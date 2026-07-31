package com.example.myapplication.presentation.maintenance

import com.example.myapplication.domain.model.DailyAlertCount
import com.example.myapplication.domain.model.MaintenancePrediction
import com.example.myapplication.domain.model.MaintenanceWorkOrder

data class MaintenanceUiState(
    val isLoading: Boolean = true,
    val predictions: List<MaintenancePrediction> = emptyList(),
    val workOrders: List<MaintenanceWorkOrder> = emptyList(),
    val dailyAlerts: List<DailyAlertCount> = emptyList(),
    val error: String? = null
)