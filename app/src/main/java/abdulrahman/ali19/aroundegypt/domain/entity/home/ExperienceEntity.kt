package abdulrahman.ali19.aroundegypt.domain.entity.home

data class ExperienceEntity(
    val placeDetails: List<PlaceDetailsEntity>
)

data class PlaceDetailsEntity(
    val id: String,
    val title: String,
    val coverPhoto: String,
    val description: String,
    val viewsNo: Int,
    val likesNo: Int,
    val recommended: Int,
    val hasVideo: Int,
    val tags: List<TagEntity>,
    val city: CityEntity,
    val tourHtml: String,
    val famousFigure: String?,
    val period: PeriodEntity?,
    val era: EraEntity?,
    val founded: String?,
    val detailedDescription: String,
    val address: String,
    val gmapLocation: GmapLocationEntity,
    /*val openingHours: Map<String, List<String>>,*/
    val translatedOpeningHours: Map<String, TranslatedOpeningHourEntity>,
    val startingPrice: Int,
    val ticketPrices: List<TicketPriceEntity>,
    val experienceTips: List<String>,
    val isLiked: Boolean?,
    val reviews: List<ReviewEntity>,
    val rating: Int,
    val reviewsNo: Int,
    val audioUrl: String?,
    val hasAudio: Boolean
)

data class TagEntity(
    val id: Int,
    val name: String?,
    val disable: Boolean?,
    val topPick: Int?
)

data class PeriodEntity(
    val id: String,
    val value: String,
    val createdA: String,
    val updatedAt: String
)

data class CityEntity(
    val id: Int?,
    val name: String?,
    val disable: Boolean?,
    val topPick: Int?
)

data class EraEntity(
    val id: String?,
    val value: String?,
    val createdAt: String?,
    val updatedAt: String?
)

data class GmapLocationEntity(
    val type: String,
    val coordinates: List<Double>
)

data class TranslatedOpeningHourEntity(
    val day: String,
    val time: String
)

data class TicketPriceEntity(
    val type: String,
    val price: Int
)

data class ReviewEntity(
    val user: String?,
    val comment: String?,
    val rating: Int?
)

