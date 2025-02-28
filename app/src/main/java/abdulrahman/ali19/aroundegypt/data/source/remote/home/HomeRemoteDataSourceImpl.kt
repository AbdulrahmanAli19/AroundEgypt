package abdulrahman.ali19.aroundegypt.data.source.remote.home

import abdulrahman.ali19.aroundegypt.data.models.ExperienceDto
import abdulrahman.ali19.aroundegypt.data.models.IntDto
import abdulrahman.ali19.aroundegypt.data.source.remote.ApiEndpoints
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class HomeRemoteDataSourceImpl(
    private val client: HttpClient
) : HomeRemoteDataSource {
    override suspend fun fetchRecommendedItems(): ExperienceDto {
        val result = client.get(ApiEndpoints.RECOMMENDED_EXPERIENCE)
        return result.body()
    }

    override suspend fun fetchRecentItems(): ExperienceDto {
        val result = client.get(ApiEndpoints.RECENT_EXPERIENCE)
        return result.body()
    }

    override suspend fun fetchSearch(title: String): ExperienceDto {
        val result = client.get(ApiEndpoints.search(searchText = title))
        return result.body()
    }

    private val json = Json { ignoreUnknownKeys = true }
    override suspend fun likePlace(id: String): IntDto {
        val result = client.post(ApiEndpoints.likeItem(id = id)) {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
        return json.decodeFromString(IntDto.serializer(), result.body<String>())
    }
}