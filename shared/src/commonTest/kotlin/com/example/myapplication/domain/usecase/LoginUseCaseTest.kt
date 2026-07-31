package com.example.myapplication.domain.usecase

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LoginUseCaseTest {

    private val loginUseCase: LoginUseCase = LoginUseCaseImpl()

    @Test
    fun login_with_empty_username_should_return_false() {
        val result = loginUseCase.invoke("", "123456")
        assertFalse(result)
    }

    @Test
    fun login_with_empty_password_should_return_false() {
        val result = loginUseCase.invoke("admin", "")
        assertFalse(result)
    }

    @Test
    fun login_with_both_empty_should_return_false() {
        val result = loginUseCase.invoke("", "")
        assertFalse(result)
    }

    @Test
    fun login_with_blank_username_should_return_false() {
        val result = loginUseCase.invoke("   ", "123456")
        assertFalse(result)
    }

    @Test
    fun login_with_wrong_username_should_return_false() {
        val result = loginUseCase.invoke("user", "123456")
        assertFalse(result)
    }

    @Test
    fun login_with_wrong_password_should_return_false() {
        val result = loginUseCase.invoke("admin", "111111")
        assertFalse(result)
    }

    @Test
    fun login_with_correct_credentials_should_return_true() {
        val result = loginUseCase.invoke("admin", "123456")
        assertTrue(result)
    }
}