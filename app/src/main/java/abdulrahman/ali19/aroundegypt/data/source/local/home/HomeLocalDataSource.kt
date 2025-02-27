package abdulrahman.ali19.aroundegypt.data.source.local.home

import abdulrahman.ali19.aroundegypt.data.models.ExperienceDto
import kotlinx.coroutines.flow.Flow

interface HomeLocalDataSource {
    suspend fun getRecommendedItems(): Flow<ExperienceDto?>
    suspend fun getRecentItems(): Flow<ExperienceDto?>
    suspend fun insertRecommendedItems(experiences: ExperienceDto?)
    suspend fun insertRecentItems(experiences: ExperienceDto?)
    suspend fun removeRecommendedItems()
    suspend fun removeRecentItems()
    suspend fun clear()
}