package abdulrahman.ali19.aroundegypt.presentation.ui.home

import abdulrahman.ali19.aroundegypt.R
import abdulrahman.ali19.aroundegypt.presentation.ui.home.compments.HomeHeader
import abdulrahman.ali19.aroundegypt.presentation.ui.home.viewmodel.HomeIntent
import abdulrahman.ali19.aroundegypt.presentation.ui.home.viewmodel.HomeViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navigateToDetails: (String) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    Column {
        HomeHeader(
            query = state.query,
            onQueryChange = { viewModel.handelIntent(HomeIntent.Search(it)) },
            onFilterClick = {},
            onMenuClick = {}
        )

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
}

