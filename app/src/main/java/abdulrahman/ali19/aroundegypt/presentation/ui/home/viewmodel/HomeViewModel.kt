package abdulrahman.ali19.aroundegypt.presentation.ui.home.viewmodel

import abdulrahman.ali19.aroundegypt.domain.usecase.home.GetRecentItemsUseCase
import abdulrahman.ali19.aroundegypt.domain.usecase.home.GetRecommendedItemsUseCase
import abdulrahman.ali19.aroundegypt.domain.usecase.home.GetSearchUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getRecentItemsUseCase: GetRecentItemsUseCase,
    private val getRecommendedItemsUseCase: GetRecommendedItemsUseCase,
    private val getSearchUseCase: GetSearchUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(isRecommendedLoading = true, isRecentLoading = true)
        }

        viewModelScope.launch {
            launch { getRecommendedPlaces() }
            launch { getRecentPlaces() }
        }

    }

    private suspend fun getRecentPlaces() {
        val result = getRecentItemsUseCase()
            .map { it?.placeDetails?.map { place -> place.toState() } ?: emptyList() }
            .first()

        _state.update {
            it.copy(recentItems = result, isRecentLoading = false)
        }
    }

    private suspend fun getRecommendedPlaces() {
        val result = getRecommendedItemsUseCase()
            .map { it?.placeDetails?.map { place -> place.toState() } ?: emptyList() }
            .first()

        _state.update {
            it.copy(recommendedItems = result, isRecommendedLoading = false)
        }
    }

    fun handelIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Search -> {
                getSearchUseCase(intent.query)
                _state.update {
                    it.copy(query = intent.query, isSearchLoading = true)
                }
            }

            is HomeIntent.Like -> {

            }
        }
    }
}