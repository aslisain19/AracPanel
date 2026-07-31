package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Notification
import com.example.myapplication.domain.model.NotificationSeverity
import kotlin.test.Test
import kotlin.test.assertEquals

class PrioritizeNotificationsUseCaseTest {

    @Test
    fun `bildirimler onem sirasina gore siralaniyor kritik once`() {
        val info = Notification(
            id = "1", machineId = "1", errorCode = "E5",
            message = "Bilgi", severity = NotificationSeverity.INFO,
            count = 1, timestamp = "1"
        )
        val warning = Notification(
            id = "2", machineId = "2", errorCode = "E4",
            message = "Uyarı", severity = NotificationSeverity.WARNING,
            count = 1, timestamp = "2"
        )
        val critical = Notification(
            id = "3", machineId = "3", errorCode = "256",
            message = "Kritik", severity = NotificationSeverity.CRITICAL,
            count = 1, timestamp = "3"
        )

        val useCase = PrioritizeNotificationsUseCase()
        val result = useCase(listOf(info, warning, critical))

        assertEquals(NotificationSeverity.CRITICAL, result[0].severity)
        assertEquals(NotificationSeverity.WARNING, result[1].severity)
        assertEquals(NotificationSeverity.INFO, result[2].severity)
    }

    @Test
    fun `onaylanmis bildirimler onaylanmamislardan sonra geliyor`() {
        val acknowledgedCritical = Notification(
            id = "1", machineId = "1", errorCode = "256",
            message = "Kritik onaylı", severity = NotificationSeverity.CRITICAL,
            count = 1, timestamp = "1", isAcknowledged = true
        )
        val unacknowledgedWarning = Notification(
            id = "2", machineId = "2", errorCode = "E4",
            message = "Uyarı onaysız", severity = NotificationSeverity.WARNING,
            count = 1, timestamp = "2", isAcknowledged = false
        )

        val useCase = PrioritizeNotificationsUseCase()
        val result = useCase(listOf(acknowledgedCritical, unacknowledgedWarning))

        assertEquals(false, result[0].isAcknowledged)
        assertEquals(true, result[1].isAcknowledged)
    }
}