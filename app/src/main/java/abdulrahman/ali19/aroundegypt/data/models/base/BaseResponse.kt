package abdulrahman.ali19.aroundegypt.data.models.base

import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse<T>(
    val meta: Meta? = null,
    val response: T? = null,
    /*val pagination: Pagination? = null*/
)

@Serializable
data class Meta(
    val code: Int,
    val errors: List<ErrorDetail>

)

@Serializable
data class ErrorDetail(
    val type: String,
    val message: String
)

/*@Serializable
data class Pagination(
    val total: Int,
    val count: Int,
    @SerialName("per_page") val perPage: Int,
    @SerialName("current_page") val currentPage: Int,
    @SerialName("total_pages") val totalPages: Int
)*/

