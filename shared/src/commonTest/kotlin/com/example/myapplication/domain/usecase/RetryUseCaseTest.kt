package com.example.myapplication.domain.usecase

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class RetryUseCaseTest {

    @Test
    fun `ilk denemede basarili olursa bir kere calisir`() = runTest {
        var attemptCount = 0
        val useCase = RetryUseCase()

        val result = useCase(maxAttempts = 3) {
            attemptCount++
            "başarılı"
        }

        assertEquals(1, attemptCount)
        assertEquals("başarılı", result)
    }

    @Test
    fun `ilk 2 denemede basarisiz olursa 3uncude basarili olur`() = runTest {
        var attemptCount = 0
        val useCase = RetryUseCase()

        val result = useCase(maxAttempts = 3) {
            attemptCount++
            if (attemptCount < 3) throw Exception("Ağ hatası")
            "başarılı"
        }

        assertEquals(3, attemptCount)
        assertEquals("başarılı", result)
    }
}