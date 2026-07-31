package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.DailyAlertCount
import com.example.myapplication.domain.model.MaintenancePrediction
import com.example.myapplication.domain.model.MaintenanceWorkOrder

interface MaintenanceRepository {
    suspend fun getMaintenancePredictions(): List<MaintenancePrediction>
    suspend fun getWorkOrders(): List<MaintenanceWorkOrder>
    suspend fun getDailyAlerts(): List<DailyAlertCount>
}