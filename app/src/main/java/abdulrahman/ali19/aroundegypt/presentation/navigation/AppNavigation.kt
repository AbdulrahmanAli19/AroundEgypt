package abdulrahman.ali19.aroundegypt.presentation.navigation

import abdulrahman.ali19.aroundegypt.presentation.navigation.route.HomeScreen
import abdulrahman.ali19.aroundegypt.presentation.ui.home.HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeScreen.route
    ) {

        composable(HomeScreen.route) {
            HomeScreen()
        }

    }
}
