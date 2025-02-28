package abdulrahman.ali19.aroundegypt.presentation.ui.details.viewmodel

import abdulrahman.ali19.aroundegypt.domain.usecase.home.GePlaceDetailsUseCase
import abdulrahman.ali19.aroundegypt.domain.usecase.home.LikePlaceUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlaceDetailsViewModel(
    private val getPlaceDetailsUseCase: GePlaceDetailsUseCase,
    private val likePlaceUseCase: LikePlaceUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PlaceDetailsState())
    val state = _state.asStateFlow()

    fun handelIntent(intent: PlaceDetailsIntent) {
        when (intent) {
            is PlaceDetailsIntent.LikePlace -> likePlace(intent.id)

            is PlaceDetailsIntent.GetPlaceDetails -> {
                viewModelScope.launch {
                    _state.update { PlaceDetailsState(isLoading = true) }
                    val result = getPlaceDetailsUseCase(intent.id)
                        .map { it.toState() }
                        .last()
                    _state.update { it.copy(isLoading = false, placeDetails = result) }
                }
            }
        }
    }

    private fun likePlace(id: String) {
        viewModelScope.launch {
            if (state.last().placeDetails.isLiked)
                return@launch
            val result = likePlaceUseCase(id)
            _state.update {
                it.copy(
                    placeDetails = it.placeDetails.copy(
                        isLiked = it.placeDetails.isLiked.not(),
                        likesNo = "${result.last()?.likesNo}"
                    )
                )
            }

        }


    }
}