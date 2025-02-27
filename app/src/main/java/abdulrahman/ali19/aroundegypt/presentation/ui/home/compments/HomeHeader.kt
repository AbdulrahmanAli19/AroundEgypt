package abdulrahman.ali19.aroundegypt.presentation.ui.home.compments

import abdulrahman.ali19.aroundegypt.R
import abdulrahman.ali19.aroundegypt.presentation.theme.SearchBarBackground
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults.Container
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit,
    onFilterClick: () -> Unit,
    query: String = "",
    onQueryChange: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        IconButton(onClick = onMenuClick) {
            Icon(
                painter = painterResource(R.drawable.ic_menu),
                contentDescription = stringResource(R.string.menu),
                modifier = modifier
                    .size(20.dp)
            )
        }

        BasicTextField(
            modifier = modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(SearchBarBackground),
            value = query,
            onValueChange = { onQueryChange(it) },
        ) {
            OutlinedTextFieldDefaults.DecorationBox(
                value = query,
                innerTextField = it,
                enabled = true,
                visualTransformation = VisualTransformation.None,
                colors = OutlinedTextFieldDefaults.colors(),
                interactionSource = interactionSource,
                label = {
                    Text(
                        text = stringResource(R.string.search_hint),
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    )
                },

                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search),
                        tint = Color.Gray
                    )
                },
                container = {
                    Container(
                        enabled = true,
                        isError = false,
                        interactionSource = interactionSource,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                        ),
                        shape = RoundedCornerShape(16),
                        focusedBorderThickness = 2.dp,
                        unfocusedBorderThickness = 2.dp,
                    )
                }
            )
        }

        IconButton(onClick = onFilterClick) {
            Icon(
                painter = painterResource(R.drawable.ic_filter),
                contentDescription = stringResource(R.string.filter),
                modifier = modifier
                    .size(24.dp)
            )
        }
    }

}

@Preview
@Composable
private fun HomeHeaderPreview() {
    HomeHeader(onFilterClick = {}, onMenuClick = {}, onQueryChange = {})
}