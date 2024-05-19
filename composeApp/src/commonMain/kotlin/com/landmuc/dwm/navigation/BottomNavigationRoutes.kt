package com.landmuc.dwm.navigation

sealed class BottomNavigationRoute(
    val route: String,
    val title: String,
    //val icon: ImageVector
) {
    object Day : BottomNavigationRoute(
        route = "day",
        title = "Day"
        //icon = Icons.Default.Home
    )
    object Week : BottomNavigationRoute(
        route = "week",
        title = "Week"
        )
    object Month : BottomNavigationRoute(
        route = "month",
        title = "Month"
        )
}

val bottomNavigationRoutes = listOf(
    BottomNavigationRoute.Day,
    BottomNavigationRoute.Week,
    BottomNavigationRoute.Month
)