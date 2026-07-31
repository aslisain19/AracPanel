package com.example.myapplication.domain.usecase

class RetryUseCase {

    suspend operator fun <T> invoke(
        maxAttempts: Int,
        block: suspend () -> T
    ): T {
        var lastException: Exception? = null

        repeat(maxAttempts) { attempt ->
            try {
                return block()
            } catch (e: Exception) {
                lastException = e
            }
        }

        throw lastException ?: Exception("Bilinmeyen hata")
    }
}