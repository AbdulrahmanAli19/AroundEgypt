package abdulrahman.ali19.aroundegypt.data.source.remote

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val TAG = "KtorHttpClient"

class KtorClient {
    fun getKtorClient(): HttpClient = HttpClient(Android) {

        engine {
            connectTimeout = 60_000
            socketTimeout = 60_000
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
                encodeDefaults = true
            })
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.i(TAG, "log: $message")
                }
            }
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response: HttpResponse ->
                Log.i(TAG, "getKoinApi: $response")
            }
        }

        install(DefaultRequest) {
            url(ApiEndpoints.BASE_URL)
            contentType(Json)
            accept(Json)
        }
        expectSuccess = true
    }
}