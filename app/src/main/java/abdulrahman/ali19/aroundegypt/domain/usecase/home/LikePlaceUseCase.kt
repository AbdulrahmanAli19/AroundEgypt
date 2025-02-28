package abdulrahman.ali19.aroundegypt.domain.usecase.home

import abdulrahman.ali19.aroundegypt.domain.repository.home.HomeRepository

class LikePlaceUseCase(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(id: String) = homeRepository.likePlace(id)
}