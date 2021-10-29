package ru.kheynov.todolistapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = primaryColor,
    primaryVariant = lightPrimaryColor,
    secondary = colorAccent,
)

private val LightColorPalette = lightColors(
    primary = primaryColor,
    primaryVariant = lightPrimaryColor,
    secondary = colorAccent,
    background = lightBackground,
    surface = Color.White,
    onPrimary = textOnPrimary,
    onSecondary = textOnSecondary,
)

@Composable
fun ToDoListAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}