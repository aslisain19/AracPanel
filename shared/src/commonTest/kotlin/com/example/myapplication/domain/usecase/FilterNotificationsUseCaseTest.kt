package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Notification
import com.example.myapplication.domain.model.NotificationSeverity
import kotlin.test.Test
import kotlin.test.assertEquals

class FilterNotificationsUseCaseTest {

    private val notifications = listOf(
        Notification(
            id = "1", machineId = "1", errorCode = "256",
            message = "Kritik hata", severity = NotificationSeverity.CRITICAL,
            count = 856, timestamp = "24/07/2026 08:12"
        ),
        Notification(
            id = "2", machineId = "2", errorCode = "E4",
            message = "Uyarı", severity = NotificationSeverity.WARNING,
            count = 724, timestamp = "24/07/2026 07:45"
        ),
        Notification(
            id = "3", machineId = "3", errorCode = "E5",
            message = "Bilgi", severity = NotificationSeverity.INFO,
            count = 1, timestamp = "23/07/2026 16:30"
        )
    )

    @Test
    fun `severity null ise tum bildirimler donuyor`() {
        val useCase = FilterNotificationsUseCase()
        val result = useCase(notifications, severity = null)
        assertEquals(3, result.size)
    }

    @Test
    fun `sadece CRITICAL secilirse tek bildirim donuyor`() {
        val useCase = FilterNotificationsUseCase()
        val result = useCase(notifications, severity = NotificationSeverity.CRITICAL)
        assertEquals(1, result.size)
        assertEquals(NotificationSeverity.CRITICAL, result.first().severity)
    }
}