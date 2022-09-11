package extensions

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.result.Result

fun Request.result(): String {
    return this.responseString().third.get()
}

fun Request.result(callback: (ByteArray) -> Unit) {
    this.response{ request, response, result ->
        when (result) {
            is Result.Failure -> {
                val ex = result.getException()
                println(ex)
            }
            is Result.Success -> {
                val data = result.get()
                callback.invoke(data)
            }
        }
    }
}

fun Request.resultString(callback: (String) -> Unit) {
    this.responseString{ request, response, result ->
        when (result) {
            is Result.Failure -> {
                val ex = result.getException()
                println(ex)
            }
            is Result.Success -> {
                val data = result.get()
                callback.invoke(data)
            }
        }
    }
}