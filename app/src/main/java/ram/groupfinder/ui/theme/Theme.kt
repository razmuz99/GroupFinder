package ram.groupfinder.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Green30,
    onPrimary = Green90,
    secondary = DarkGreen80,
    onSecondary = DarkGreen20,
    error = Red80,
    onError = Red20,
    background = Grey20,
    onBackground = Grey90,
    surface = GreenGrey30,
    onSurface = GreenGrey80,
)

private val LightColorPalette = lightColors(
    primary = Green40,
    onPrimary = Color.White,
    secondary = DarkGreen40,
    onSecondary = Color.White,
    error = Red40,
    onError = Color.White,
    background = Grey99,
    onBackground = Grey10,
    surface = GreenGrey90,
    onSurface = GreenGrey30,
)

@Composable
fun GroupFinderTheme(
    content: @Composable () -> Unit
) {
    val colors = when {
        isSystemInDarkTheme() -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}