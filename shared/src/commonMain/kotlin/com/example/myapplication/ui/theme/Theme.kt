package com.example.myapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.example.myapplication.ui.localization.AppStrings
import com.example.myapplication.ui.localization.EnStrings
import com.example.myapplication.ui.localization.TrStrings

// Dil sözlüğünü Decorator akışına dahil etmek için CompositionLocal
val LocalStrings = staticCompositionLocalOf<AppStrings> { EnStrings }

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = SurfaceWhite,
    background = BackgroundLight,
    surface = SurfaceWhite,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlueDark,
    onPrimary = PrimaryBlue,
    background = BackgroundDark,
    surface = SurfaceDark,
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark
)

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isTurkish: Boolean = true,
    content: @Composable () -> Unit
) {
    // 1. Gelen parametreye göre dile karar veriyoruz
    val strings = if (isTurkish) TrStrings else EnStrings

    // 2. Temaya göre renk şemasını seçiyoruz
    val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme

    // Decorator mantığı: İçerideki content çağrılmadan önce dil ve renk verilerini sarmalıyoruz
    CompositionLocalProvider(
        LocalStrings provides strings
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = MaterialTheme.typography,
            content = content
        )
    }
}

// UI katmanından nesneye kolay erişim köprüsü
object AppTheme {
    val strings: AppStrings
        @Composable
        get() = LocalStrings.current
}