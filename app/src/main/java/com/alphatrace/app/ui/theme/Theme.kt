package com.alphatrace.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AlphaTraceColorScheme = lightColorScheme(
    primary = Coral,
    onPrimary = White,
    primaryContainer = LightCoral,
    onPrimaryContainer = DarkText,
    secondary = Gold,
    onSecondary = DarkText,
    background = WarmCream,
    onBackground = DarkText,
    surface = White,
    onSurface = DarkText,
    surfaceVariant = WarmCream,
    onSurfaceVariant = DarkText
)

@Composable
fun AlphaTraceTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AlphaTraceColorScheme,
        typography = AlphaTraceTypography,
        content = content
    )
}
