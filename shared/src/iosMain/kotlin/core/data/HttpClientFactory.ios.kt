package core.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class IosHttpClientFactory : HttpClientFactory {
    override fun create(): HttpClient {
        return HttpClient(Darwin) {
            install(ContentNegotiation) {
                json(Json {
                    encodeDefaults = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
            }
        }
    }
}


actual fun getHttpClientFactory(): HttpClientFactory = IosHttpClientFactory()

