package abdulrahman.ali19.aroundegypt.data.mappers

import abdulrahman.ali19.aroundegypt.data.models.CityDto
import abdulrahman.ali19.aroundegypt.data.models.EraDto
import abdulrahman.ali19.aroundegypt.data.models.ExperienceDto
import abdulrahman.ali19.aroundegypt.data.models.GmapLocationDto
import abdulrahman.ali19.aroundegypt.data.models.PeriodDto
import abdulrahman.ali19.aroundegypt.data.models.PlaceDetailsDto
import abdulrahman.ali19.aroundegypt.data.models.ReviewDto
import abdulrahman.ali19.aroundegypt.data.models.TagDto
import abdulrahman.ali19.aroundegypt.data.models.TicketPriceDto
import abdulrahman.ali19.aroundegypt.data.models.TranslatedOpeningHourDto
import abdulrahman.ali19.aroundegypt.domain.entity.home.CityEntity
import abdulrahman.ali19.aroundegypt.domain.entity.home.EraEntity
import abdulrahman.ali19.aroundegypt.domain.entity.home.ExperienceEntity
import abdulrahman.ali19.aroundegypt.domain.entity.home.GmapLocationEntity
import abdulrahman.ali19.aroundegypt.domain.entity.home.PeriodEntity
import abdulrahman.ali19.aroundegypt.domain.entity.home.PlaceDetailsEntity
import abdulrahman.ali19.aroundegypt.domain.entity.home.ReviewEntity
import abdulrahman.ali19.aroundegypt.domain.entity.home.TagEntity
import abdulrahman.ali19.aroundegypt.domain.entity.home.TicketPriceEntity
import abdulrahman.ali19.aroundegypt.domain.entity.home.TranslatedOpeningHourEntity

fun ExperienceDto.toEntity() = ExperienceEntity(
    placeDetails = places.map { it.toEntity() }
)

fun PlaceDetailsDto.toEntity() = PlaceDetailsEntity(
    id = id ?: "null",
    title = title ?: "null",
    coverPhoto = coverPhoto ?: "null",
    description = description ?: "null",
    viewsNo = viewsNo ?: -1,
    likesNo = likesNo ?: -1,
    recommended = recommended ?: -1,
    hasVideo = hasVideo ?: -1,
    tags = tags.orEmpty().map { it.toEntity() },
    city = city.toEntity(),
    tourHtml = tourHtml ?: "null",
    famousFigure = famousFigure,
    period = period.toEntity(),
    era = era?.toEntity(),
    founded = founded,
    detailedDescription = detailedDescription ?: "null",
    address = address ?: "null",
    gmapLocation = gmapLocation.toEntity(),
    openingHours = openingHours ?: emptyMap(),
    translatedOpeningHours = translatedOpeningHours.orEmpty().mapValues { it.value.toEntity() },
    startingPrice = startingPrice ?: -1,
    ticketPrices = ticketPrices.orEmpty().map { it.toEntity() },
    experienceTips = experienceTips ?: emptyList(),
    isLiked = isLiked,
    reviews = reviews.orEmpty().map { it.toEntity() },
    rating = rating ?: -1,
    reviewsNo = reviewsNo ?: -1,
    audioUrl = audioUrl,
    hasAudio = hasAudio ?: false
)

fun TagDto?.toEntity() =
    TagEntity(
        id = this?.id ?: -1,
        name = this?.name,
        disable = this?.disable,
        topPick = this?.topPick
    )

fun PeriodDto?.toEntity() =
    PeriodEntity(
        id = this?.id ?: "null",
        value = this?.value ?: "null",
        createdA = this?.createdA ?: "null",
        updatedAt = this?.updatedAt ?: "null",
    )

fun CityDto?.toEntity() =
    CityEntity(id = this?.id, name = this?.name, disable = this?.disable, topPick = this?.topPick)

fun EraDto?.toEntity() =
    EraEntity(
        id = this?.id,
        value = this?.value,
        createdAt = this?.createdAt,
        updatedAt = this?.updatedAt
    )

fun GmapLocationDto?.toEntity() =
    GmapLocationEntity(type = this?.type ?: "null", coordinates = this?.coordinates ?: emptyList())

fun TranslatedOpeningHourDto?.toEntity() =
    TranslatedOpeningHourEntity(day = this?.day ?: "null", time = this?.time ?: "null")

fun TicketPriceDto?.toEntity() =
    TicketPriceEntity(type = this?.type ?: "null", price = this?.price ?: -1)

fun ReviewDto?.toEntity() =
    ReviewEntity(user = this?.user, comment = this?.comment, rating = this?.rating)