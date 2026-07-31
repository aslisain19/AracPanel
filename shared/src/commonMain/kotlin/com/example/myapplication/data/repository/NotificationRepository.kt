package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Notification

interface NotificationRepository {
    suspend fun getNotifications(): List<Notification>
}