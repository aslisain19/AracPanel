package com.example.myapplication.data.repository

import com.example.myapplication.data.mapper.toDomain
import com.example.myapplication.data.remote.MaintenanceApi
import com.example.myapplication.domain.model.DailyAlertCount
import com.example.myapplication.domain.model.MaintenancePrediction
import com.example.myapplication.domain.model.MaintenanceWorkOrder
import com.example.myapplication.domain.repository.MaintenanceRepository

class MaintenanceRepositoryImpl(
    private val api: MaintenanceApi
) : MaintenanceRepository {

    override suspend fun getMaintenancePredictions(): List<MaintenancePrediction> {
        return api.getMaintenancePredictions().map { it.toDomain() }
    }

    override suspend fun getWorkOrders(): List<MaintenanceWorkOrder> {
        return api.getWorkOrders().map { it.toDomain() }
    }

    override suspend fun getDailyAlerts(): List<DailyAlertCount> {
        return api.getDailyAlerts().map { it.toDomain() }
    }
}