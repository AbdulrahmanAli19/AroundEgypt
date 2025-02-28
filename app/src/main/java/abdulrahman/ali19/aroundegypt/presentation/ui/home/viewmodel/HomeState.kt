package abdulrahman.ali19.aroundegypt.presentation.ui.home.viewmodel

data class HomeState(
    val recommendedItems: List<PlaceState> = emptyList(),
    val recentItems: List<PlaceState> = emptyList(),
    val searchResult: List<PlaceState> = emptyList(),
    val query: String = "",
    val isRecommendedLoading: Boolean = false,
    val isRecentLoading: Boolean = false,
    val isSearchLoading: Boolean = false,
    val error: String = ""
)

data class PlaceState(
    val id: String,
    val title: String,
    val cover: String,
    val numberOfViews: String,
    val numberOfLikes: String,
    val isLiked: Boolean,
)

sealed class HomeIntent {
    data class Search(val query: String) : HomeIntent()
    data class Like(val placeId: String, val position: Int, val likeType: LikeTypes) : HomeIntent()
}

enum class LikeTypes {
    RECOMMENDED,
    RECENT,
    SEARCH
}