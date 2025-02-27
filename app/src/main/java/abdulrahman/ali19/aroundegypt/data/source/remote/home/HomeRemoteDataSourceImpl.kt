package abdulrahman.ali19.aroundegypt.data.source.remote.home

import abdulrahman.ali19.aroundegypt.data.models.ExperienceDto
import abdulrahman.ali19.aroundegypt.data.source.remote.ApiEndpoints
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post

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
        val result = client.post(ApiEndpoints.search(searchText = title))
        return result.body()
    }
}