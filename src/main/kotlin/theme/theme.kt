import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import theme.DarkColorPalette
import theme.typography

@Composable
fun ReportlyTheme(
    content: @Composable () -> Unit
) {

    val darkColors = DarkColorPalette

    val typography = typography

    MaterialTheme(
        colors = darkColors,
        typography = typography,
        content = content
    )
}