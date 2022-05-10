package com.sudhindra.composepokedex.routes

import com.sudhindra.composepokedex.ui.screens.PokemonScreenType

sealed class Route(val route: String, val title: String) {
    object Dashboard : Route("dashboard", "Pokédex")
    object Pokemon : Route("pokemon/{pokemonScreenType}/{typeRegionId}", "Pokémon") {
        fun defaultRoute() = "pokemon/${PokemonScreenType.ALL_POKEMON}/${0}"
    }

    object Items : Route("items", "Items")
    object Locations : Route("locations", "Locations")
    object Types : Route("types", "Types")
    object Regions : Route("regions", "Regions")
    object Favourites : Route("favourites", "Favourites")
}
