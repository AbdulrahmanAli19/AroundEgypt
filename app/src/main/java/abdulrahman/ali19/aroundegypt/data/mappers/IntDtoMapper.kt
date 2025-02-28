package abdulrahman.ali19.aroundegypt.data.mappers

import abdulrahman.ali19.aroundegypt.data.models.IntDto
import abdulrahman.ali19.aroundegypt.domain.entity.home.IntEntity

fun IntDto.toEntity() = IntEntity(data ?: 0)