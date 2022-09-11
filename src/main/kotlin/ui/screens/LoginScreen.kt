package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.get
import di.koinModule
import kotlinx.coroutines.launch
import ui.components.CircularIndeterminateProgressBar
import viewmodel.LoginViewModel

@Composable
fun LoginScreen(nextScreen: () -> Unit) {

    Koin({ modules(koinModule) }) {
        val viewModel = get<LoginViewModel>()
        viewModel.viewModelScope

        var loading by remember { mutableStateOf(false) }
        var loadingText by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularIndeterminateProgressBar(loading)
            Text(loadingText)
            Button(enabled = loading.not(), onClick = {
                loading = true
                viewModel.viewModelScope.launch {
                    viewModel.authorizationUsingPkce(){ loadingText = it }
                    viewModel.spotifyClientApi(){ loadingText = it }
//                    viewModel.createClientPlaylists(){ loadingText = it }

                    nextScreen()
                }
            }) {
                Text("Login")
            }
        }
    }
}

