package core.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class AndroidHttpClientFactory : HttpClientFactory {
    override fun create(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    encodeDefaults = true
                })
            }
        }
    }
}

actual fun getHttpClientFactory(): HttpClientFactory = AndroidHttpClientFactory()