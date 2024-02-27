package core.data

import io.ktor.client.HttpClient

interface HttpClientFactory {
    fun create(): HttpClient
}


expect fun getHttpClientFactory(): HttpClientFactory
