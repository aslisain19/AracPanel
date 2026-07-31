package com.example.myapplication.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.usecase.LoginUseCaseImpl
import com.example.myapplication.ui.theme.AppTheme
import androidx.compose.foundation.background

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    // Kullanıcı girdilerini tutacak durumlar (state)
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    // Az önce test ettiğimiz gerçek iş mantığı sınıfı
    val loginUseCase = remember { LoginUseCaseImpl() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Başlık - Dil sözlüğünden dinamik olarak geliyor
            Text(
                text = AppTheme.strings.welcomeMessage,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            // Kullanıcı Adı Girdisi
            OutlinedTextField(
                value = username,
                onValueChange = {
                    username = it
                    isError = false
                },
                label = { Text(AppTheme.strings.usernamePlaceholder) },
                modifier = Modifier.fillMaxWidth(),
                isError = isError
            )

            // Şifre Girdisi
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    isError = false
                },
                label = { Text(AppTheme.strings.passwordPlaceholder) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                isError = isError
            )

            // Hata Durumu Mesajı
            if (isError) {
                Text(
                    text = AppTheme.strings.invalidCredentialsError,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Giriş Butonu
            Button(
                onClick = {
                    // Test ettiğimiz use-case fonksiyonunu tetikliyoruz
                    val success = loginUseCase(username, password)
                    if (success) {
                        onLoginSuccess() // Giriş başarılıysa bir sonraki ekrana yönlendir
                    } else {
                        isError = true // Başarısızsa ekranda hata göster
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(text = AppTheme.strings.loginButton)
            }
        }
    }
}