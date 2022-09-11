package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import compose.icons.CssGgIcons
import compose.icons.cssggicons.Check

@Composable
fun ImageWithCheckBox(
    imageModifier: Modifier = Modifier.width(64.dp).aspectRatio(1f).padding(4.dp),
    imageBitmap: ImageBitmap,
    isSelected: Boolean,
    callback: (Boolean) -> Unit
) {

    val imageBitmap =  imageBitmap

    var isSelected by remember { mutableStateOf(isSelected) }

    Box(modifier = Modifier.clickable {
        isSelected = isSelected.not()
        callback.invoke(isSelected)
    }) {
        AsyncImage(
            load = { imageBitmap },
            painterFor = { remember { BitmapPainter(it) } },
            contentDescription = "Sample",
            modifier = imageModifier.width(64.dp).aspectRatio(1f).padding(4.dp)
        )
        Icon(
            modifier = Modifier
                .border(
                    BorderStroke(1.dp, SolidColor(Color.Black)),
                    CircleShape
                )
                .width(12.dp)
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .background(if (isSelected) Color.Green else Color.Gray),

            imageVector = CssGgIcons.Check,
            tint = Color.White,
            contentDescription = "",
        )
    }
}