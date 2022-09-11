package viewmodel

import com.adamratzman.spotify.models.SimplePlaylist
import db.ClientPlaylists
import db.DestinationPlaylists
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class SelectDestPlaylistViewModel() : BaseViewModel() {

    val playlistList = get<ClientPlaylists>().playlists

    fun setDestPlaylists(list: List<SimplePlaylist>) {
        val destPlaylists = get<DestinationPlaylists> { parametersOf(list) }
    }
}