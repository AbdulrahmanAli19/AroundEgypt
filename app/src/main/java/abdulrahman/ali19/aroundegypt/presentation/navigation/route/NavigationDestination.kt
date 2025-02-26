package abdulrahman.ali19.aroundegypt.presentation.navigation.route

sealed class NavigationDestination(
    val route: String
) {

    fun withArgs(vararg args: Any?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

data object HomeScreen : NavigationDestination("home")
