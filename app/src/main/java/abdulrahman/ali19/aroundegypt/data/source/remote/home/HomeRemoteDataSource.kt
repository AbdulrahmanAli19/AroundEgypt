package abdulrahman.ali19.aroundegypt.data.source.remote.home

import abdulrahman.ali19.aroundegypt.data.models.ExperienceDto

interface HomeRemoteDataSource {
    suspend fun fetchRecommendedItems(): List<ExperienceDto>
    suspend fun fetchRecentItems(): List<ExperienceDto>
    suspend fun fetchSearch(title:String): List<ExperienceDto>
}