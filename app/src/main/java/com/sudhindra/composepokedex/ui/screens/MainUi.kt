package com.sudhindra.composepokedex.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sudhindra.composepokedex.routes.Route
import com.sudhindra.composepokedex.ui.components.CustomAppBar
import com.sudhindra.composepokedex.ui.utils.LocalTheme

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

    @Composable
    fun toggleTheme() {
        LocalTheme.current.toggleTheme()
    }

    val toggleThemeComposableAction = ComposableAction {
        toggleTheme()
    }

    Scaffold(topBar = {
        CustomAppBar(
            Modifier.clickable { toggleThemeComposableAction() },
            label = title,
            showBackButton = currentRoute != Route.Dashboard.route,
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

interface NonComposableCall {
    operator fun invoke()
}

@Composable
fun ComposableAction(
    action: @Composable () -> Unit
): NonComposableCall {
    var executionState by remember { mutableStateOf(false) }

    if (executionState) {
        action()
        executionState = false
    }

    return object : NonComposableCall {
        override fun invoke() {
            executionState = true
        }
    }
}
