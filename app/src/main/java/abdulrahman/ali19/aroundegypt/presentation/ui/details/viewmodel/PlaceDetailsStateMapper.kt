package abdulrahman.ali19.aroundegypt.presentation.ui.details.viewmodel

import abdulrahman.ali19.aroundegypt.domain.entity.home.PlaceDetailsEntity

fun PlaceDetailsEntity?.toState(): PlaceDetails {
    return PlaceDetails(
        id = this?.id ?: "",
        title = this?.title ?: "",
        description = this?.description ?: "",
        image = this?.coverPhoto ?: "",
        location = this?.address ?: "",
        likesNo = "${this?.likesNo}",
        viewsNo = "${this?.viewsNo}",
        isLiked = this?.isLiked ?: false
    )
}