package abdulrahman.ali19.aroundegypt.data.source.remote.home

import abdulrahman.ali19.aroundegypt.data.models.ExperienceDto
import abdulrahman.ali19.aroundegypt.data.models.IntDto

interface HomeRemoteDataSource {
    suspend fun fetchRecommendedItems(): ExperienceDto?
    suspend fun fetchRecentItems(): ExperienceDto?
    suspend fun fetchSearch(title: String): ExperienceDto?
    suspend fun likePlace(id: String): IntDto?
}