package viewmodel

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.adamratzman.spotify.models.SimplePlaylist
import db.ClientPlaylists
import db.SourcePlaylist
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class SelectSourcePlaylistViewModel : BaseViewModel(){

    val playlistList: SnapshotStateList<SimplePlaylist> = get<ClientPlaylists>().playlists

    fun setSourcePlaylist(simplePlaylist: SimplePlaylist) {
        val sourcePlaylist = get<SourcePlaylist>(){ parametersOf(simplePlaylist) }
    }

}