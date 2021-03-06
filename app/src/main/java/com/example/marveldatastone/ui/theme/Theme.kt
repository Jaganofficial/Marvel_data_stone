package com.example.marveldatastone.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = WHITE_COLOR,
    secondary = Teal200,
    onSecondary = Color.Black,
    onPrimary = Color.Black
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = BLACK_COLOR,
    secondary = Teal200,
    onSecondary = Color.Black,
    onPrimary = BLACK_COLOR
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    ,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MarvelDataStoneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
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