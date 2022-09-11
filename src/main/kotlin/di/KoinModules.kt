package di

import kotlinx.coroutines.*
import login.SpotifyLogin
import org.koin.dsl.module
import viewmodel.LoginViewModel
import viewmodel.MainViewModel
import viewmodel.SelectDestPlaylistViewModel
import viewmodel.SelectSourcePlaylistViewModel

val koinModule = module {
    single{
        runBlocking {
            SpotifyLogin.authorize()
        }
    }

    single { parametersHolder ->  db.SourcePlaylist(parametersHolder.get())}
    single { parametersHolder ->  db.DestinationPlaylists(parametersHolder.get())}

    single{ db.ClientPlaylists() }


    single { LoginViewModel() }
    factory { SelectSourcePlaylistViewModel() }
    factory { SelectDestPlaylistViewModel() }
    single { MainViewModel() }

    factory { CoroutineScope(Dispatchers.Default) }
}

interface SuspendableProvider<T> {
    suspend fun get(): T
    val isCompleted: Boolean
}
fun <T> suspendableLazy(provider: suspend () -> T) = object : SuspendableProvider<T> {
    private val computed = GlobalScope.async(start = CoroutineStart.LAZY) { provider() }
    override val isCompleted: Boolean
        get() = computed.isCompleted
    override suspend fun get() = computed.await()
}
