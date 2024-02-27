package core.domain

import io.ktor.client.HttpClient

class KtorApiRequestImpl(
    private val httpClient: HttpClient
) : HttpApiRequest {
}