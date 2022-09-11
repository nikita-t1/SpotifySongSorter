package db

import com.adamratzman.spotify.SpotifyClientApi
import com.adamratzman.spotify.models.SimplePlaylist
import com.adamratzman.spotify.models.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.CoroutineScope

class SourcePlaylist(playlist: SimplePlaylist) : KoinComponent {

    private val api by inject<SpotifyClientApi>()
    private val _trackList: MutableList<Track> = mutableListOf()
    val trackList: List<Track> get() = _trackList

    init {
        CoroutineScope(Dispatchers.IO).launch {
            var offset = 0
            try {
                do {
                    val pagingObject = api.playlists.getPlaylistTracks(
                        playlist = playlist.id,
                        offset = offset,
                        limit = 50
                    )
                    pagingObject.toList().filterNotNull().forEach { playlistTrack ->
                        playlistTrack.track?.asTrack?.let { track -> _trackList.add(track) }
                    }
                    offset+=50
                } while (pagingObject.isEmpty().not())

            } catch (_: IndexOutOfBoundsException) {
            }
        }
    }
}