package abdulrahman.ali19.aroundegypt.presentation.ui.details

import abdulrahman.ali19.aroundegypt.R
import abdulrahman.ali19.aroundegypt.presentation.theme.PrimaryColor
import abdulrahman.ali19.aroundegypt.presentation.ui.details.viewmodel.PlaceDetails
import abdulrahman.ali19.aroundegypt.presentation.ui.details.viewmodel.PlaceDetailsIntent
import abdulrahman.ali19.aroundegypt.presentation.ui.details.viewmodel.PlaceDetailsViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailsBottomSheet(
    onDismissRequest: (() -> Unit),
    placeId: String,
    modifier: Modifier = Modifier,
    viewModel: PlaceDetailsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) { viewModel.handelIntent(PlaceDetailsIntent.GetPlaceDetails(id = placeId)) }

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = {
            onDismissRequest.invoke()
        },
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false),
        dragHandle = { BottomSheetDefaults.ExpandedShape },
    ) {
        Column {
            PlaceDetailsHeader(
                placeState = state.placeDetails,
                onLikeClick = { viewModel.handelIntent(PlaceDetailsIntent.LikePlace(it)) }
            )
            HorizontalDivider(modifier = Modifier.padding(16.dp))
            PlaceDetailsBody(placeState = state.placeDetails)
        }
    }
}

@Composable
fun PlaceDetailsHeader(
    placeState: PlaceDetails,
    onLikeClick: (placeId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box {
            var isLoading by remember { mutableStateOf(true) }
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(placeState.image)
                    .crossfade(true)
                    .build(),
                contentDescription = placeState.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .placeholder(
                        visible = isLoading,
                        highlight = PlaceholderHighlight.shimmer()
                    ),
                onSuccess = { isLoading = false },
                onError = { isLoading = false }
            )

            Icon(
                modifier = modifier
                    .padding(8.dp)
                    .size(24.dp)
                    .align(Alignment.BottomEnd),
                painter = painterResource(R.drawable.ic_pictures),
                contentDescription = stringResource(R.string.info),
                tint = Color.White
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .padding(8.dp)
                    .align(Alignment.BottomStart),
            ) {

                Text(
                    text = placeState.viewsNo,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White
                    ),
                    modifier = Modifier.padding(4.dp)
                )

                Icon(
                    modifier = modifier
                        .size(24.dp),
                    painter = painterResource(R.drawable.ic_view),
                    contentDescription = stringResource(R.string.views),
                    tint = Color.White
                )
            }
        }

        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    text = placeState.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )

                Text(
                    text = placeState.location,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = Color.Gray
                    ),
                    modifier = Modifier.padding(16.dp)
                )
            }

            Row {
                Icon(
                    modifier = modifier
                        .size(24.dp),
                    painter = painterResource(R.drawable.ic_share),
                    contentDescription = stringResource(R.string.share),
                    tint = PrimaryColor
                )
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                IconButton(onClick = { onLikeClick(placeState.id) }) {
                    Icon(
                        modifier = modifier
                            .size(24.dp),
                        painter = painterResource(R.drawable.ic_like),
                        contentDescription = stringResource(R.string.views),
                        tint = if (placeState.isLiked) PrimaryColor else MaterialTheme.colorScheme.secondary
                    )
                }

                Text(
                    text = placeState.likesNo,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(4.dp)
                )
            }

        }

    }
}

@Composable
fun PlaceDetailsBody(
    placeState: PlaceDetails,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Text(
            text = stringResource(R.string.description),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        )

        Text(
            text = placeState.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        )
    }
}