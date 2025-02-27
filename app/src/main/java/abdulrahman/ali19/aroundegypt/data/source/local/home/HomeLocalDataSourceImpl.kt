package abdulrahman.ali19.aroundegypt.data.source.local.home

import abdulrahman.ali19.aroundegypt.data.models.ExperienceDto
import abdulrahman.ali19.aroundegypt.data.models.PlaceDetailsDto
import abdulrahman.ali19.aroundegypt.data.source.local.LocalDataProvider
import abdulrahman.ali19.aroundegypt.data.source.local.LocalKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.builtins.ListSerializer

class HomeLocalDataSourceImpl(
    private val dataProvider: LocalDataProvider
) : HomeLocalDataSource {
    override suspend fun getRecommendedItems(): Flow<ExperienceDto?> {
        val recommendedItems = dataProvider.getObject(
            key = LocalKeys.RECOMMENDED,
            serializer = ExperienceDto.serializer()
        )
        return recommendedItems
    }

    override suspend fun getRecentItems(): Flow<ExperienceDto?> {
        val recommendedItems = dataProvider.getObject(
            key = LocalKeys.RECENT,
            serializer = ExperienceDto.serializer()
        )
        return recommendedItems
    }

    override suspend fun insertRecommendedItems(experiences: ExperienceDto?) {
        dataProvider.saveObject(
            key = LocalKeys.RECOMMENDED,
            value = ListSerializer(PlaceDetailsDto.serializer())
        )
    }

    override suspend fun insertRecentItems(experiences: ExperienceDto?) {
        dataProvider.saveObject(
            key = LocalKeys.RECENT,
            value = ExperienceDto.serializer()
        )
    }

    override suspend fun removeRecommendedItems() {
        dataProvider.remove(LocalKeys.RECOMMENDED)
    }

    override suspend fun removeRecentItems() {
        dataProvider.remove(LocalKeys.RECENT)
    }

    override suspend fun clear() {
        dataProvider.clear()
    }
}