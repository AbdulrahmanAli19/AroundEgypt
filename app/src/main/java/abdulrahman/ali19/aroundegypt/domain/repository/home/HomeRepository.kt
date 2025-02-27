package abdulrahman.ali19.aroundegypt.domain.repository.home

import abdulrahman.ali19.aroundegypt.domain.entity.home.ExperienceEntity
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getRecommendedItems(): Flow<ExperienceEntity?>
    suspend fun getRecentItems(): Flow<ExperienceEntity?>
    suspend fun getSearch(title: String): Flow<ExperienceEntity?>
}