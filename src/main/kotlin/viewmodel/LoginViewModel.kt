package viewmodel

import com.adamratzman.spotify.SpotifyClientApi
import const.SpotifyCredentials
import kotlinx.coroutines.delay
import login.SpotifyLogin
import org.koin.core.component.inject
import java.awt.Desktop
import java.net.URI

class LoginViewModel : BaseViewModel() {

    init {
        println("init")
    }

    suspend fun authorizationUsingPkce(callback: (String) -> Unit) {
        callback.invoke("Generate Authorization URL")
        val url = SpotifyLogin.getPkceAuthorizationUrl()
        Desktop.getDesktop().browse(URI(url))

        callback.invoke("Wait for Auth Code")
        waitForAuthCode()
    }

    private suspend fun waitForAuthCode() {
        while (SpotifyCredentials.AUTH_CODE.isBlank()) {
            delay(100)
        }
    }

    fun spotifyClientApi(callback: (String) -> Unit): SpotifyClientApi {
        callback.invoke("Create SpotifyClientApi")
        val api by inject<SpotifyClientApi>()
        callback.invoke("SpotifyClientApi Created")
        return api
    }

}

