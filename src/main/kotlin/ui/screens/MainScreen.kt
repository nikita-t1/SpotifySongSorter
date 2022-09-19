package ui.screens

import ColorThief
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toAwtImage
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import cache.SpotifyImageCache
import compose.icons.CssGgIcons
import compose.icons.cssggicons.PlayButton
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.get
import di.koinModule
import extensions.toComposeColor
import kotlinx.coroutines.launch
import ui.components.*
import viewmodel.MainViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen() {
    Koin({ modules(koinModule) }) {
        val viewModel = get<MainViewModel>()
        val trackList = remember { viewModel.sourcePlaylist.trackList }
        val playlists = remember { (viewModel.destPlaylists.playlistTracks) }

        BoxWithVerticalScrollbar { listState ->
            LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
                items(trackList) { track ->
                    val stroke by mutableStateOf(viewModel.currentTrack == track.id)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                            .border(BorderStroke(if (stroke) 10.dp else 0.dp, Color.Green)),
                        elevation = 6.dp
                    ) {
                        val colorList =
                            ColorThief.getColor(loadImageBitmap(loadAndCacheImageFileFromUrl(track.album.images.first().url)).toAwtImage())

                        val brush = Brush.horizontalGradient(
                            colors = listOf(
                                colorList.first().toComposeColor(),
                                Color.White
                            )
                        )
                        Row(modifier = Modifier.background(brush)) {
                            var showPlayIcon by remember { mutableStateOf(false) }
                            Box(modifier = Modifier
                                .width(150.dp)
                                .aspectRatio(1f)
                            ) {
                                Image(
                                    bitmap = loadImageBitmap(loadAndCacheImageFileFromUrl(track.album.images.first().url)),
                                    "",
                                    modifier = Modifier
                                        .width(150.dp)
                                        .aspectRatio(1f)
                                        .onPointerEvent(PointerEventType.Enter) {
                                            showPlayIcon = true
                                        }
                                        .onPointerEvent(PointerEventType.Exit) {
                                            showPlayIcon = false
                                        },
                                )
                                if (showPlayIcon){
                                    Icon(
                                        modifier = Modifier
                                            .width(48.dp)
                                            .align(Alignment.Center)
                                            .background(Color.Transparent),

                                        imageVector = CssGgIcons.PlayButton,
                                        tint = Color.Green,
                                        contentDescription = "",
                                    )
                                }

                            }
                            SongAndArtistColumn(
                                track.name,
                                track.artists.first().name,
                                colorList[1].toComposeColor(),
                                colorList[2].toComposeColor()
                            ) {
                                LazyRow(
                                    modifier = Modifier.fillMaxHeight().fillMaxHeight(),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    items(playlists) { (playlist, tracks) ->
                                        var isTrackInPlaylist by mutableStateOf(tracks.any { it.id == track.id })
                                        ImageWithCheckBox(
                                            Modifier,
                                            imageBitmap = SpotifyImageCache.getImage(playlist.images.first().url),
                                            isTrackInPlaylist
                                        ) {
                                            if (isTrackInPlaylist) {//remove
                                                viewModel.viewModelScope.launch {
                                                    viewModel.spotifyApi.playlists.removePlayableFromClientPlaylist(
                                                        playlist.id,
                                                        track.uri
                                                    )
                                                }
                                            } else {// add
                                                viewModel.viewModelScope.launch {
                                                    viewModel.spotifyApi.playlists.addPlayableToClientPlaylist(
                                                        playlist.id,
                                                        track.uri
                                                    )
                                                }
                                            }
                                            isTrackInPlaylist = isTrackInPlaylist.not()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


            }
        }

    }
}