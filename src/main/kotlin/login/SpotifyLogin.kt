package login

import com.adamratzman.spotify.*
import const.SpotifyCredentials

object SpotifyLogin {

    fun getPkceAuthorizationUrl(): String {
        val codeChallenge = getSpotifyPkceCodeChallenge(SpotifyCredentials.CODE_VERIFIER) // helper method
        val url: String = getSpotifyPkceAuthorizationUrl(
            SpotifyScope.PLAYLIST_READ_PRIVATE,
            SpotifyScope.STREAMING,
            SpotifyScope.USER_LIBRARY_READ,
            SpotifyScope.PLAYLIST_MODIFY_PRIVATE,
            SpotifyScope.USER_FOLLOW_READ,
            SpotifyScope.USER_LIBRARY_MODIFY,
            SpotifyScope.USER_READ_PLAYBACK_STATE,
            SpotifyScope.USER_MODIFY_PLAYBACK_STATE,
            SpotifyScope.USER_READ_PLAYBACK_POSITION,
            SpotifyScope.USER_READ_CURRENTLY_PLAYING,
            SpotifyScope.USER_READ_RECENTLY_PLAYED,
            clientId = SpotifyCredentials.CLIENT_ID,
            redirectUri = SpotifyCredentials.REDIRECT_URI,
            codeChallenge = codeChallenge
        )
        return url
    }

    suspend fun authorize(authCode: String = SpotifyCredentials.AUTH_CODE): SpotifyClientApi {
        val api = spotifyClientPkceApi(
            SpotifyCredentials.CLIENT_ID, // optional. include for token refresh
            SpotifyCredentials.REDIRECT_URI, // optional. include for token refresh
            authCode,
            SpotifyCredentials.CODE_VERIFIER // the same code verifier you used to generate the code challenge
        ) {
            retryWhenRateLimited = false
        }.build()
        return api
    }

}

