package abdulrahman.ali19.aroundegypt.presentation.ui.home.viewmodel


import abdulrahman.ali19.aroundegypt.domain.entity.home.PlaceDetailsEntity

fun PlaceDetailsEntity.toState(): PlaceState {
    return PlaceState(
        id = this.id,
        title = title,
        cover = coverPhoto,
        numberOfViews = "$viewsNo",
        numberOfLikes = "$likesNo",
        isLiked = isLiked ?: false
    )
}