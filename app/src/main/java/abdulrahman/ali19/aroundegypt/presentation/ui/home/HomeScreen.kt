package abdulrahman.ali19.aroundegypt.presentation.ui.home

import abdulrahman.ali19.aroundegypt.R
import abdulrahman.ali19.aroundegypt.presentation.ui.home.compments.HomeHeader
import abdulrahman.ali19.aroundegypt.presentation.ui.home.compments.PlaceBanner
import abdulrahman.ali19.aroundegypt.presentation.ui.home.viewmodel.HomeIntent
import abdulrahman.ali19.aroundegypt.presentation.ui.home.viewmodel.HomeViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import org.koin.androidx.compose.koinViewModel
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    navigateToDetails: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val pagerState = rememberPagerState(pageCount = { state.recommendedItems.size })
    LazyColumn {
        item {
            HomeHeader(
                query = state.query,
                onQueryChange = { viewModel.handelIntent(HomeIntent.Search(it)) },
                onFilterClick = {},
                onMenuClick = {}
            )
        }

        if (state.query.isEmpty()) {

            item {
                Text(
                    text = stringResource(R.string.welcome),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                )

                Text(
                    text = stringResource(R.string.welcome_msg),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            item {
                Text(
                    text = stringResource(R.string.recommended_experiences),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }

            item {
                HorizontalPager(
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = 16.dp),

                    ) { page ->
                    Box(
                        modifier = Modifier
                            .graphicsLayer {
                                val pageOffset = (
                                        (pagerState.currentPage - page) + pagerState
                                            .currentPageOffsetFraction
                                        ).absoluteValue
                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            }
                    ) {
                        PlaceBanner(
                            place = state.recommendedItems[page],
                            onLikeClick = { place -> viewModel.handelIntent(HomeIntent.Like(place.id)) },
                            isRecommended = true
                        )
                    }

                }
            }

            item {
                Text(
                    text = stringResource(R.string.most_recent),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }

            items(state.recentItems) {
                PlaceBanner(
                    place = it,
                    onLikeClick = { place -> viewModel.handelIntent(HomeIntent.Like(place.id)) }
                )
            }
        } else {
            item {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    if (state.isSearchLoading)
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.TopCenter)
                        )
                }
            }
            items(state.searchResult) {
                PlaceBanner(
                    place = it,
                    onLikeClick = { place -> viewModel.handelIntent(HomeIntent.Like(place.id)) }
                )
            }
        }
    }
}

