package abdulrahman.ali19.aroundegypt.presentation.ui.home.viewmodel

import abdulrahman.ali19.aroundegypt.domain.usecase.home.GetRecentItemsUseCase
import abdulrahman.ali19.aroundegypt.domain.usecase.home.GetRecommendedItemsUseCase
import abdulrahman.ali19.aroundegypt.domain.usecase.home.GetSearchUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class HomeViewModel(
    private val getRecentItemsUseCase: GetRecentItemsUseCase,
    private val getRecommendedItemsUseCase: GetRecommendedItemsUseCase,
    private val getSearchUseCase: GetSearchUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _searchQuery = MutableStateFlow("")

    init {
        _state.update {
            it.copy(isRecommendedLoading = true, isRecentLoading = true)
        }

        viewModelScope.launch {
            launch { getRecommendedPlaces() }
            launch { getRecentPlaces() }
        }

        viewModelScope.launch {
            _searchQuery
                .debounce(1000L)
                .distinctUntilChanged()
                .filter { it.isNotBlank() }
                .flatMapLatest { query ->
                    getSearchUseCase(query)
                }
                .collect { results ->
                    _state.update {
                        it.copy(
                            searchResult = results?.placeDetails?.map { place ->
                                place.toState()
                            } ?: emptyList(),
                            isSearchLoading = false
                        )
                    }
                }
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
                _searchQuery.value = intent.query
                _state.update {
                    it.copy(isSearchLoading = true, query = intent.query)
                }
            }

            is HomeIntent.Like -> {

            }
        }
    }
}