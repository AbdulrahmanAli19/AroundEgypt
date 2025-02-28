package abdulrahman.ali19.aroundegypt.data.repository.home

import abdulrahman.ali19.aroundegypt.data.mappers.toEntity
import abdulrahman.ali19.aroundegypt.data.models.ExperienceDto
import abdulrahman.ali19.aroundegypt.data.source.local.home.HomeLocalDataSource
import abdulrahman.ali19.aroundegypt.data.source.remote.home.HomeRemoteDataSource
import abdulrahman.ali19.aroundegypt.domain.entity.home.ExperienceEntity
import abdulrahman.ali19.aroundegypt.domain.entity.home.IntEntity
import abdulrahman.ali19.aroundegypt.domain.entity.home.PlaceDetailsEntity
import abdulrahman.ali19.aroundegypt.domain.repository.home.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class HomeRepositoryImpl(
    private val remoteDataSource: HomeRemoteDataSource,
    private val localDataSource: HomeLocalDataSource
) : HomeRepository {
    override suspend fun getRecommendedItems(): Flow<ExperienceEntity?> = flow {
        localDataSource.getRecommendedItems().map {
            emit(it?.toEntity())
        }

        val result = remoteDataSource.fetchRecommendedItems()
        localDataSource.removeRecommendedItems()
        localDataSource.insertRecommendedItems(result)
        emit(result?.toEntity())
    }

    override suspend fun getRecentItems(): Flow<ExperienceEntity?> = flow {
        localDataSource.getRecentItems().map {
            emit(it?.toEntity())
        }

        val result = remoteDataSource.fetchRecentItems()
        localDataSource.removeRecentItems()
        localDataSource.insertRecentItems(result)
        emit(result?.toEntity())

    }


    override suspend fun getSearch(title: String): Flow<ExperienceEntity?> = flow {
        val local = localDataSource.getRecommendedItems()

        val mappedData = local.first()?.places?.filter {
            it.title?.contains(title, ignoreCase = true) == true
        }

        emit(ExperienceDto(mappedData ?: emptyList()).toEntity())

        val result = remoteDataSource.fetchSearch(title)
        emit(result?.toEntity())

    }

    override suspend fun likePlace(id: String): Flow<IntEntity?> = flow {
        val result = remoteDataSource.likePlace(id)
        emit(result?.toEntity())
    }

    override suspend fun getPlaceDetails(id: String): Flow<PlaceDetailsEntity?> = flow {
        val result = remoteDataSource.fetchPlaceDetails(id)
        emit(result?.data?.toEntity())
    }
}
