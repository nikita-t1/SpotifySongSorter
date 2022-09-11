//package ui.components
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.aspectRatio
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import kotlinx.coroutines.withContext
//import kotlin.coroutines.CoroutineContext
//import kotlin.coroutines.EmptyCoroutineContext
//
//@Composable
//fun <T> callbackAsState(
//    context: CoroutineContext = EmptyCoroutineContext,
//    key: Any,
//    callback: suspend () -> T?,
//): State<T?> {
//    return produceState<T?>(initialValue = null, key1 = key) {
//        if (context == EmptyCoroutineContext) {
//            callback()?.let { this@produceState.value = it }
//        } else {
//            withContext(context) {
//                callback()?.let { this@produceState.value = it }
//            }
//        }
//    }
//}
//
//@Composable
//fun SpotifyImage(
//    url: String?,
//    size: Dp = 64.dp,
//    modifier: Modifier = Modifier,
//){
//    val imageState = url?.let {
//        // shortcut the happy path where the image is in memory and doesn't need a recomposition to load it
//        remember(url) {
//            SpotifyImageCache.getInMemory(url)?.let { mutableStateOf(it) }
//        } ?: callbackAsState(key = url) { SpotifyImageCache.get(url = url, scope = scope) }
//    }
//
//    val imageModifier = modifier
//        .size(size)
//        .aspectRatio(1f)
//
//    val imageBitmap = imageState?.value
//    if (imageBitmap == null) {
//        Box(imageModifier.background(Color.LightGray))
//    } else {
//        Image(
//            bitmap = imageBitmap,
//            contentDescription = "",
//            modifier = imageModifier,
//        )
//    }
//
//}
