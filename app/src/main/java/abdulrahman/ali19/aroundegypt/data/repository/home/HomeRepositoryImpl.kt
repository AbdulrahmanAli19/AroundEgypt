package abdulrahman.ali19.aroundegypt.data.repository.home

import abdulrahman.ali19.aroundegypt.data.mappers.toEntity
import abdulrahman.ali19.aroundegypt.data.source.local.home.HomeLocalDataSource
import abdulrahman.ali19.aroundegypt.data.source.remote.home.HomeRemoteDataSource
import abdulrahman.ali19.aroundegypt.domain.entity.home.ExperienceEntity
import abdulrahman.ali19.aroundegypt.domain.repository.home.HomeRepository
import kotlinx.coroutines.flow.Flow
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
        emit(result?.toEntity())

        localDataSource.removeRecommendedItems()
        localDataSource.insertRecommendedItems(result)
    }

    override suspend fun getRecentItems(): Flow<ExperienceEntity?> = flow {
        localDataSource.getRecentItems().map {
            emit(it?.toEntity())
        }

        val result = remoteDataSource.fetchRecentItems()
        emit(result?.toEntity())

        localDataSource.removeRecentItems()
        localDataSource.insertRecentItems(result)
    }


    override suspend fun getSearch(title: String): Flow<ExperienceEntity?> = flow {
        val result = remoteDataSource.fetchSearch(title)
        emit(result?.toEntity())

    }
}
