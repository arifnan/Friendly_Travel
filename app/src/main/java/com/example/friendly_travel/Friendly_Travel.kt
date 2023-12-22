package com.example.friendly_travel

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.friendly_travel.components.BottomBar
import com.example.friendly_travel.navigation.Screen
import com.example.friendly_travel.screen.detail.DetailWisataScreen
import com.example.friendly_travel.screen.favorite.FavoriteScreen
import com.example.friendly_travel.screen.home.HomeScreen

@Composable
fun Friendly_Travel(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailWisata.route) {
                BottomBar(navController)
            }

        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { wisataId ->
                        navController.navigate(Screen.DetailWisata.createRoute(wisataId))
                    }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = { wisataId ->
                        navController.navigate(Screen.DetailWisata.createRoute(wisataId))
                    }
                )
            }
           // composable(Screen.Profile.route) {
             //   AboutScreen()
            //}
            composable(
                route = Screen.DetailWisata.route,
                arguments = listOf(
                    navArgument("wisataId") { type = NavType.IntType }
                )
            ) {
                val id = it.arguments?.getInt("wisataId") ?: -1
                DetailWisataScreen(
                    wisataId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }

        }
    }
}