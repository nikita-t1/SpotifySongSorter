package ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp),
)

val rounded20 = RoundedCornerShape(20.dp)

data class ShapeValues(
    var topStart: Int = 0,
    var bottomStart: Int = 0,
    var topEnd: Int = 0,
    var bottomEnd: Int = 0,
)
internal val LocalReboundShapes = staticCompositionLocalOf { Shapes() }

val BottomSheetShape = RoundedCornerShape(
    topStart = 20.dp,
    topEnd = 20.dp,
    bottomEnd = 0.dp,
    bottomStart = 0.dp
)
