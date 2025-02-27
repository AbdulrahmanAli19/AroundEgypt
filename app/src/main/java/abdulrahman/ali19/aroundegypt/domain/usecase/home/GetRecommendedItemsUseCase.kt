package abdulrahman.ali19.aroundegypt.domain.usecase.home

import abdulrahman.ali19.aroundegypt.domain.repository.home.HomeRepository

class GetRecommendedItemsUseCase(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke() = homeRepository.getRecommendedItems()
}