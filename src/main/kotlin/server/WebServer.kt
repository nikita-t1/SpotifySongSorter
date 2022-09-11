package server

import const.SpotifyCredentials
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun WebServer(port: Int): NettyApplicationEngine {
    return embeddedServer(Netty, port = port) {
        routing {
            get("/") {
                call.respondText("Hello, world!")
            }
            get("/callback") {
                val code = call.request.call.parameters["code"]!!
                SpotifyCredentials.AUTH_CODE = code
                call.respondText("Code received: $code")
            }
        }
    }
}