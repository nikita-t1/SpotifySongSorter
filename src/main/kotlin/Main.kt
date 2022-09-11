// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.koinModule
import org.koin.core.context.GlobalContext.startKoin
import server.WebServer
import ui.LoginScreen
import ui.Screen
import ui.screens.MainScreen
import ui.screens.SelectDestPlaylistScreen
import ui.screens.SelectSourcePlaylistScreen
import ui.theme.MyThemeWrapper

class Main

fun main() {
    startKoin {
        printLogger()
        modules(koinModule)
    }
    application {

        WebServer(port = 8080).start(wait = false)

        val icon = painterResource("icons/icon.png")
        Window(onCloseRequest = ::exitApplication, icon = icon, title = "SpotifySongSorter") {
            MyThemeWrapper {

                var screenState by remember { mutableStateOf<Screen>(Screen.LoginScreen) }
                when (val screen = screenState) {
                    Screen.LoginScreen -> LoginScreen { screenState = Screen.SelectSourcePlaylistScreen }
                    Screen.SelectSourcePlaylistScreen -> SelectSourcePlaylistScreen {
                        screenState = Screen.SelectDestPlaylistScreen
                    }

                    Screen.SelectDestPlaylistScreen -> SelectDestPlaylistScreen { screenState = Screen.MainScreen }
                    Screen.MainScreen -> MainScreen()
                }
            }
        }
    }
}