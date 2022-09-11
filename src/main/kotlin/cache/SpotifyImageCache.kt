package cache

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import com.github.kittinunf.fuel.httpDownload
import com.github.kittinunf.fuel.httpGet
import extensions.result
import org.jetbrains.skia.ColorSpace
import org.jetbrains.skia.Image
import java.io.File
import java.util.concurrent.ConcurrentHashMap


object SpotifyImageCache {
    private val path = File(".cache/images")
    var map: MutableMap<String, ByteArray> = ConcurrentHashMap()

    init {
        if (path.exists().not()) {
            path.mkdirs()
        }
    }

    fun preloadImage(url: String) {
        val filename = url.substringAfterLast("/")
        val fullPath = path.resolve(filename)
        url.httpGet()
//            .fileDestination { _, _ -> fullPath }
            .result { result ->
                map[filename] = result
            }
//        fullPath.deleteOnExit()
    }

    fun getImage(url: String): ImageBitmap {
        val filename = url.substringAfterLast("/")
        return try {
            Image.makeFromEncoded(map[filename]!!).toComposeImageBitmap()
        } catch (_: NullPointerException){
            ImageBitmap(200,200)
        }

    }

}