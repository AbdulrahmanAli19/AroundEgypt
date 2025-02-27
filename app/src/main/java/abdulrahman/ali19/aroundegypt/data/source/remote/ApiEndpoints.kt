package abdulrahman.ali19.aroundegypt.data.source.remote

object ApiEndpoints {
    const val BASE_URL = "https://aroundegypt.34ml.com/api/v2/"

    const val RECENT_EXPERIENCE = "experiences"
    const val RECOMMENDED_EXPERIENCE = "experiences?filter[recommended]=true"

    fun search(searchText: String) = "experiences?filter[title]=$searchText"
    fun singleItem(id: Int) = "experiences/$id"
    fun likeItem(id: Int) = "experiences/$id}/like"
}
