package abdulrahman.ali19.aroundegypt.domain.usecase.home

import abdulrahman.ali19.aroundegypt.domain.repository.home.HomeRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class GetSearchUseCase(
    private val homeRepository: HomeRepository
) {
    @OptIn(FlowPreview::class)
    operator fun invoke(title: String) = flow {
        emitAll(homeRepository.getSearch(title))
    }.debounce(300L)
}