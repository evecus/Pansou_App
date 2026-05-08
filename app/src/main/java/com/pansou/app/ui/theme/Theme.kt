package com.pansou.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PrimaryBlue = Color(0xFF2E7CF6)
private val PrimaryBlueDark = Color(0xFF1A5DC8)
private val Background = Color(0xFFFFFFFF)
private val Surface = Color(0xFFFFFFFF)
private val SurfaceVariant = Color(0xFFF5F7FA)
private val OnSurface = Color(0xFF1A1A2E)
private val OnSurfaceVariant = Color(0xFF6B7280)
private val Outline = Color(0xFFE5E7EB)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDBEAFF),
    onPrimaryContainer = Color(0xFF0A3977),
    secondary = Color(0xFF6B7280),
    onSecondary = Color.White,
    background = Background,
    onBackground = OnSurface,
    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVariant,
    outline = Outline,
    error = Color(0xFFE53935),
    onError = Color.White,
)

@Composable
fun PanSouTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
