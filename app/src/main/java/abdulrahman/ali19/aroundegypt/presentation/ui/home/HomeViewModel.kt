package abdulrahman.ali19.aroundegypt.presentation.ui.home

import abdulrahman.ali19.aroundegypt.domain.usecase.home.GetRecentItemsUseCase
import abdulrahman.ali19.aroundegypt.domain.usecase.home.GetRecommendedItemsUseCase
import abdulrahman.ali19.aroundegypt.domain.usecase.home.GetSearchUseCase
import androidx.lifecycle.ViewModel

class HomeViewModel(
    private val getRecentItemsUseCase: GetRecentItemsUseCase,
    private val getRecommendedItemsUseCase: GetRecommendedItemsUseCase,
    private val getSearchUseCase: GetSearchUseCase
) : ViewModel() {

}