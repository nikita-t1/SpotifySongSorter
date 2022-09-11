package ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun SongAndArtistColumn(
    song: String,
    artist: String,
    songColor: Color,
    artistColor: Color,
    content: @Composable() () -> Unit
) {
    Column(
        modifier = Modifier.padding(15.dp).height(120.dp)
    ) {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.W900,
                        color = songColor
                    )
                ) {
                    append(song)
                }
            }
        )
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.W900,
                        color = artistColor
                    )
                ) {
                    append(song)
                }
            })
        content()

    }
}