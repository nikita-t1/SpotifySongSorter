package db

import androidx.compose.runtime.mutableStateListOf
import com.adamratzman.spotify.SpotifyClientApi
import com.adamratzman.spotify.models.SimplePlaylist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ClientPlaylists() : KoinComponent {

    private val spotifyApi by inject<SpotifyClientApi>()
    val playlists = mutableStateListOf<SimplePlaylist>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            var offset = 0
            var allEmpty = false
            try {
                while (allEmpty.not()) {
                    val pagingObject = spotifyApi.playlists.getClientPlaylists(50, offset)

                    if (pagingObject.toList().isEmpty()) allEmpty = true

                    pagingObject
                        .toList()
                        .filterNotNull()
//                        .filter { it.owner.id == spotifyApi.getUserId() }
                        .forEach { playlists.add(it); println(it.name) }

                    offset += 50
                }
            } catch (_: IndexOutOfBoundsException) {
            }
        }
    }
}