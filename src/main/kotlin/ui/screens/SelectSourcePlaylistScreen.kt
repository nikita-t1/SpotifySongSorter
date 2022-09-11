package ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.isSecondaryPressed
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.get
import di.koinModule
import ui.components.CircularIndeterminateProgressBar
import ui.components.ImageWithCheckBox
import ui.components.loadAndCacheImageFileFromUrl
import ui.components.loadImageBitmap
import viewmodel.SelectSourcePlaylistViewModel
import java.awt.Desktop
import java.net.URI

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SelectSourcePlaylistScreen(openDestScreen: () -> Unit) {
    Koin({ modules(koinModule) }) {
        val viewModel = get<SelectSourcePlaylistViewModel>()
        val playlistList = viewModel.playlistList

        CircularIndeterminateProgressBar(playlistList.isEmpty())
        if (playlistList.isEmpty().not()){
            Box(modifier = Modifier.padding(16.dp)) {
                val sourceList = remember { mutableListOf<Int>() }
                var showSnackbar by remember { mutableStateOf(true) }
                Column {

                    Text("Select the Source Playlist", modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp))

                    LazyVerticalGrid(GridCells.Adaptive(128.dp), modifier = Modifier.padding(bottom = 64.dp)) {// Adaptive seems to hav a bug...

                        items(playlistList.size) { index ->
                            Column {
                                var isSelected by remember { mutableStateOf(sourceList.contains(index)) }
                                val file = loadAndCacheImageFileFromUrl(playlistList[index].images.first().url)
                                val image = loadImageBitmap(file)

                                ImageWithCheckBox(
                                    Modifier.width(Int.MAX_VALUE.dp).aspectRatio(1f).padding(4.dp)
                                        .onPointerEvent(PointerEventType.Press) {
                                        if (it.buttons.isSecondaryPressed){
                                            Desktop.getDesktop().browse(URI(playlistList[index].images.first().url))
                                        }
                                    },
                                    imageBitmap = image,
                                    isSelected = isSelected,
                                    callback = {
                                        if (it) sourceList.add(index) else sourceList.remove(index)
                                        isSelected = isSelected.not()
                                        showSnackbar = (sourceList.isEmpty() || sourceList.size > 1)
                                    }
                                )
                                Text(playlistList[index].name + " " + playlistList[index].tracks.total, Modifier.padding(4.dp))
                            }
                        }
                    }
                }

                Button(onClick = {
                    viewModel.setSourcePlaylist(playlistList[sourceList.first()])
                    openDestScreen()
                }, modifier = Modifier.align(Alignment.BottomCenter), enabled = showSnackbar.not()) {
                    Text("Next")
                }
                if (showSnackbar) {
                    Snackbar(modifier = Modifier.align(Alignment.BottomCenter)) {
                        Text(modifier = Modifier.align(Alignment.Center), text = "Select exactly ONE Playlist")
                    }
                }
            }
        }
    }
}