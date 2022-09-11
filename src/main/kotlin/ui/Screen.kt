package ui

sealed class Screen {
    object LoginScreen : Screen()
    object SelectSourcePlaylistScreen : Screen()
    object SelectDestPlaylistScreen : Screen()
    object MainScreen : Screen()
}