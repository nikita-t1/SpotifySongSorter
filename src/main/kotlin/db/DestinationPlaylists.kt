package db

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import cache.SpotifyImageCache
import com.adamratzman.spotify.SpotifyClientApi
import com.adamratzman.spotify.models.SimplePlaylist
import com.adamratzman.spotify.models.Track
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DestinationPlaylists(private val list: List<SimplePlaylist>) : KoinComponent {

    private val api by inject<SpotifyClientApi>()

    val playlistTracks: SnapshotStateList<Pair<SimplePlaylist, List<Track>>> =  mutableStateListOf()

    init {
        runBlocking {
            val tracklist = mutableListOf<Track>()
            for (simplePlaylist in list) {
                SpotifyImageCache.preloadImage(simplePlaylist.images.first().url)
                try {
                    var offset = 0
                    do {
                        val pagingObject = api.playlists.getPlaylistTracks(
                            playlist = simplePlaylist.id,
                            offset = offset,
                            limit = 50
                        )
                        pagingObject.toList().filterNotNull().forEach { playlistTrack ->
                            playlistTrack.track?.asTrack?.let { track -> tracklist.add(track) }
                        }
                        offset += 50
                    } while (pagingObject.isEmpty().not())
                    playlistTracks.add(Pair(simplePlaylist, tracklist.map { it.copy() }))
                    tracklist.clear()
                } catch (_: Exception) {

                }
            }
        }
    }


}