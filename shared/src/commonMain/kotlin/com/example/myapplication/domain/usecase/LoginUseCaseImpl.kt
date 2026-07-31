package com.example.myapplication.domain.usecase

class LoginUseCaseImpl : LoginUseCase {
    override fun invoke(username: String, password: String): Boolean {
        if (username.isBlank() || password.isBlank()) {
            return false
        }
        return username == "admin" && password == "123456"
    }
}