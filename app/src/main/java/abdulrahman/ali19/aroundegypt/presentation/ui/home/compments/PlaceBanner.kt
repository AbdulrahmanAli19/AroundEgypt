package abdulrahman.ali19.aroundegypt.presentation.ui.home.compments

import abdulrahman.ali19.aroundegypt.R
import abdulrahman.ali19.aroundegypt.presentation.theme.PrimaryColor
import abdulrahman.ali19.aroundegypt.presentation.ui.home.viewmodel.PlaceState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer


@Composable
fun PlaceBanner(
    place: PlaceState,
    onLikeClick: (PlaceState) -> Unit,
    modifier: Modifier = Modifier,
    isRecommended: Boolean = false,
) {
    Column(
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {

        Box {
            var isLoading by remember { mutableStateOf(true) }

            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(place.cover)
                    .crossfade(true)
                    .build(),
                contentDescription = place.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .placeholder(
                        visible = isLoading,
                        highlight = PlaceholderHighlight.Companion.shimmer()
                    ),
                onSuccess = { isLoading = false },
                onError = { isLoading = false }
            )

            if (isRecommended)
                Row(
                    modifier = modifier
                        .padding(8.dp)
                        .align(Alignment.TopStart)
                        .clip(MaterialTheme.shapes.medium)
                        .background(Color.Gray.copy(alpha = 0.5f)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = modifier
                            .padding(8.dp)
                            .size(18.dp),
                        painter = painterResource(R.drawable.ic_star),
                        contentDescription = stringResource(R.string.recommended),
                        tint = PrimaryColor
                    )

                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = stringResource(R.string.recommended).uppercase(),
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = Color.White,
                        )
                    )
                }

            Icon(
                modifier = modifier
                    .padding(8.dp)
                    .size(37.dp)
                    .align(Alignment.Center),
                painter = painterResource(R.drawable.ic_360),
                contentDescription = stringResource(R.string.like),
                tint = Color.White
            )

            Icon(
                modifier = modifier
                    .padding(8.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd),
                painter = painterResource(R.drawable.ic_info),
                contentDescription = stringResource(R.string.info),
                tint = Color.White
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
                    text = place.numberOfViews,
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
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {

            Text(
                text = place.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(16.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = place.numberOfLikes,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(4.dp)
                )

                IconButton(onClick = { onLikeClick(place) }) {
                    Icon(
                        modifier = modifier
                            .size(24.dp),
                        painter = painterResource(R.drawable.ic_like),
                        contentDescription = stringResource(R.string.like),
                        tint = if (place.isLiked) PrimaryColor else MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }

    }
}