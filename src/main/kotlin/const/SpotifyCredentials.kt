package const

object SpotifyCredentials {


    private val credentialsString = this::class.java.getResourceAsStream("/client.credentials")?.bufferedReader()?.readText() ?: this::class.java.getResourceAsStream("/client.credentials.example")!!.bufferedReader().readText()
    private val credentialsMap = convertStringToMap(credentialsString)
    val CODE_VERIFIER = credentialsMap.getOrDefault("CODE_VERIFIER", "Lorem_ipsum_dolor_sit_amet,_consetetur_sadipscing.")
    val CLIENT_ID = credentialsMap.getOrDefault("CLIENT_ID", "g78az03ralczfb5jj2ynbv6cplmu6is8")
    val REDIRECT_URI = credentialsMap.getOrDefault("REDIRECT_URI", "http://localhost:8080/callback")
    var AUTH_CODE = credentialsMap.getOrDefault("AUTH_CODE", "")

    private fun convertStringToMap(mapAsString: String): Map<String, String> {
        return mapAsString.lines().associate {
            val (left, right) = it.split("=")
            left to right.ifBlank { "" }
        }
    }

}