package com.sudhindra.composepokedex.ui.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sudhindra.composepokedex.routes.Route
import com.sudhindra.composepokedex.ui.components.CustomAppBar

val allRoutes = listOf(
    Route.Dashboard,
    Route.Pokemon,
    Route.Items,
    Route.Locations,
    Route.Types,
    Route.Regions,
    Route.Favourites,
)

@Composable
fun MainUi() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Route.Dashboard.route

    val title: String =
        allRoutes.find { it.route == currentRoute }?.title ?: Route.Dashboard.title

    Scaffold(topBar = {
        CustomAppBar(
            label = title,
            showBackButton = currentRoute != Route.Dashboard.route
        )
    }) {
        NavHost(navController, startDestination = Route.Dashboard.route) {
            composable(Route.Dashboard.route) { DashboardUi(navController) }
            composable(
                Route.Pokemon.route,
                arguments = listOf(navArgument("typeRegionId") { type = NavType.IntType })
            ) { backStackEntry ->
                PokemonUi(
                    pokemonScreenType = backStackEntry.arguments?.getString("pokemonScreenType"),
                    typeRegionId = backStackEntry.arguments?.getInt("typeRegionId")
                )
            }
            composable(Route.Items.route) { ItemsUi() }
            composable(Route.Locations.route) { LocationsUi() }
            composable(Route.Types.route) { TypesUi(navHostController = navController) }
            composable(Route.Regions.route) { RegionsUi(navHostController = navController) }
            composable(Route.Favourites.route) { FavouritesUi() }
        }
    }
}
