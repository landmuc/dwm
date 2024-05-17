package com.landmuc.dwm.Navigation

sealed class BottomNavigationRoute(val route: String) {
    object Day : BottomNavigationRoute("day")
    object Week : BottomNavigationRoute("week")
    object Month : BottomNavigationRoute("month")
}

val bottomNavigationRoutes = listOf(
    BottomNavigationRoute.Day,
    BottomNavigationRoute.Week,
    BottomNavigationRoute.Month
)