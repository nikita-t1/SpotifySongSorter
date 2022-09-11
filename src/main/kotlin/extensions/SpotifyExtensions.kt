package extensions

enum class Limit{
    UNLIMITED,
}

//
//suspend fun Track.isPartOfPlaylist(): List<SimplePlaylist> {
//    val listOfPlaylist = mutableListOf<SimplePlaylist>()
//
//
//    val playlists: Map<SimplePlaylist, List<Track>> = Spotify.clientPlaylists
//
//    for (playlist in playlists){
//        playlist.value.filter {
//            it.id == this.id
//        }
//            .forEach { _ ->
//                listOfPlaylist.add(playlist.key)
//            }
//    }
//    return listOfPlaylist.distinct()
//}