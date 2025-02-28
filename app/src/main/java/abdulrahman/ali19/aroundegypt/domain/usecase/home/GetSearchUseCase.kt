package abdulrahman.ali19.aroundegypt.domain.usecase.home

import abdulrahman.ali19.aroundegypt.domain.repository.home.HomeRepository

class GetSearchUseCase(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(title: String) = homeRepository.getSearch(title)
}