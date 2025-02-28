package abdulrahman.ali19.aroundegypt.data.models

import abdulrahman.ali19.aroundegypt.data.models.base.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class IntDto(
    val data: Int? = null
) : BaseResponse<IntDto>()