package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.adamratzman.spotify.SpotifyClientApi
import db.DestinationPlaylists
import db.SourcePlaylist
import org.koin.core.component.get
import org.koin.core.component.inject

class MainViewModel: BaseViewModel() {

    val spotifyApi by inject<SpotifyClientApi>()
    val sourcePlaylist = get<SourcePlaylist>()
    val destPlaylists = get<DestinationPlaylists>()
    var currentTrack by mutableStateOf("")

}