package com.example.myapplication.data.repository

import com.example.myapplication.data.mapper.toDomain
import com.example.myapplication.data.remote.NotificationApi
import com.example.myapplication.domain.model.Notification
import com.example.myapplication.domain.repository.NotificationRepository

class NotificationRepositoryImpl(
    private val api: NotificationApi
) : NotificationRepository {

    override suspend fun getNotifications(): List<Notification> {
        return api.getNotifications().map { it.toDomain() }
    }
}