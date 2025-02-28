package abdulrahman.ali19.aroundegypt.data.models

import abdulrahman.ali19.aroundegypt.data.models.base.BaseResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExperienceDto(
    @SerialName("data") val places: List<PlaceDetailsDto> = emptyList()
) : BaseResponse<ExperienceDto>()

@Serializable
data class SingleExperienceDto(
    @SerialName("data") val data: PlaceDetailsDto?
) : BaseResponse<ExperienceDto>()

@Serializable
data class PlaceDetailsDto(
    val id: String? = null,
    val title: String? = null,
    @SerialName("cover_photo") val coverPhoto: String? = null,
    val description: String? = null,
    @SerialName("views_no") val viewsNo: Int? = null,
    @SerialName("likes_no") val likesNo: Int? = null,
    val recommended: Int? = null,
    @SerialName("has_video") val hasVideo: Int? = null,
    val tags: List<TagDto>? = null,
    val city: CityDto? = null,
    @SerialName("tour_html") val tourHtml: String? = null,
    @SerialName("famous_figure") val famousFigure: String? = null,
    val period: PeriodDto? = null,
    val era: EraDto? = null,
    val founded: String? = null,
    @SerialName("detailed_description") val detailedDescription: String? = null,
    val address: String? = null,
    @SerialName("gmap_location") val gmapLocation: GmapLocationDto? = null,
    /*@SerialName("opening_hours") val openingHours: Map<String, List<String>>? = null,*/
    @SerialName("translated_opening_hours") val translatedOpeningHours: Map<String, TranslatedOpeningHourDto>? = null,
    @SerialName("starting_price") val startingPrice: Int? = null,
    @SerialName("ticket_prices") val ticketPrices: List<TicketPriceDto>? = null,
    @SerialName("experience_tips") val experienceTips: List<String>? = null,
    @SerialName("is_liked") val isLiked: Boolean? = null,
    val reviews: List<ReviewDto>? = null,
    val rating: Int? = null,
    @SerialName("reviews_no") val reviewsNo: Int? = null,
    @SerialName("audio_url") val audioUrl: String? = null,
    @SerialName("has_audio") val hasAudio: Boolean? = null
)

@Serializable
data class PeriodDto(
    val id: String? = null,
    val value: String? = null,
    @SerialName("created_at") val createdA: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)

@Serializable
data class TagDto(
    val id: Int? = null,
    val name: String? = null,
    val disable: Boolean? = null,
    @SerialName("top_pick") val topPick: Int? = null
)

@Serializable
data class CityDto(
    val id: Int? = null,
    val name: String? = null,
    val disable: Boolean? = null,
    @SerialName("top_pick") val topPick: Int? = null
)

@Serializable
data class EraDto(
    val id: String? = null,
    val value: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null
)

@Serializable
data class GmapLocationDto(
    val type: String? = null,
    val coordinates: List<Double>? = null
)

@Serializable
data class TranslatedOpeningHourDto(
    val day: String? = null,
    val time: String? = null
)

@Serializable
data class TicketPriceDto(
    val type: String? = null,
    val price: Int? = null
)

@Serializable
data class ReviewDto(
    val user: String? = null,
    val comment: String? = null,
    val rating: Int? = null
)