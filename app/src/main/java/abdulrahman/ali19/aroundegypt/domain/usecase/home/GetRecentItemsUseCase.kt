package abdulrahman.ali19.aroundegypt.domain.usecase.home

import abdulrahman.ali19.aroundegypt.domain.repository.home.HomeRepository

class GetRecentItemsUseCase(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke() = homeRepository.getRecentItems()
}