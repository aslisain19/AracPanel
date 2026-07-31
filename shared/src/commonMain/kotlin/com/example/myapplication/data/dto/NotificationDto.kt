package com.example.myapplication.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NotificationDto(
    val id: String,
    val machineId: String,
    val errorCode: String,
    val message: String,
    val severity: String,
    val count: Int,
    val timestamp: String
)