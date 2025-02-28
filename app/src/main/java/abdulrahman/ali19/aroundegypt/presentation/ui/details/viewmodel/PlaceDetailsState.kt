package abdulrahman.ali19.aroundegypt.presentation.ui.details.viewmodel

data class PlaceDetailsState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val placeDetails: PlaceDetails = PlaceDetails()
)

data class PlaceDetails(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val image: String = "",
    val location: String = "",
    val likesNo: String = "",
    val viewsNo: String = "",
    val isLiked: Boolean = false
)

sealed class PlaceDetailsIntent {
    data class LikePlace(val id: String) : PlaceDetailsIntent()
    data class GetPlaceDetails(val id: String) : PlaceDetailsIntent()
}