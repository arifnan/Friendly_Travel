package com.example.friendly_travel.navigation

sealed class Screen(val route: String) {
    object Register:Screen("register")
    object Login:Screen("login")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Seacrh : Screen("search")
    object Favorite :Screen("favorite")
    object Filter : Screen("filter")
    object DetailWisata : Screen("home/{wisataId}") {
        fun createRoute(wisataId: Int) = "home/$wisataId"
    }
    object Category : Screen("home/{categoryId}") {
        fun createRoute(categoryId: Int) = "home/$categoryId"
    }
}