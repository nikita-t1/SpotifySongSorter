package extensions

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb

fun java.awt.Color.toComposeColor(): Color {
    this.colorSpace
    return Color(this.rgb)
}


fun Color.isDark() = luminance() < 0.5f

fun Color.darkenColor(fraction: Double): Color {
    return Color(Math.max(this.toArgb() - this.toArgb() * fraction, 0.0).toInt())
}

fun Color.lightenColor(fraction: Double): Color {
    return Color(Math.min(this.toArgb() + this.toArgb() * fraction, 255.0).toInt())
}