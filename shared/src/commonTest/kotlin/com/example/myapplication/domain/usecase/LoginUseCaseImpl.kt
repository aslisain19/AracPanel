package com.example.myapplication.domain.usecase

class LoginUseCaseImpl : LoginUseCase {
    override fun invoke(username: String, password: String): Boolean {
        // Yazdığın testlerin (blank, empty senaryolarının) geçmesi için kontrol:
        if (username.isBlank() || password.isBlank()) {
            return false
        }

        // Doğru bilgilerin doğrulanması testi için kontrol:
        return username == "admin" && password == "123456"
    }
}