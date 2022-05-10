package com.sudhindra.composepokedex.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.sudhindra.composepokedex.activities.DetailsActivity
import com.sudhindra.composepokedex.constants.BundleKeys
import com.sudhindra.composepokedex.ui.components.PokemonCard
import com.sudhindra.composepokedex.ui.paging.LazyPagingColumn
import com.sudhindra.composepokedex.utils.createIntent
import com.sudhindra.composepokedex.viemodel.pokemon.PokemonViewModel

enum class PokemonScreenType {
    ALL_POKEMON,
    TYPED_POKEMON,
    REGIONAL_POKEMON
}

@Composable
fun PokemonUi(
    pokemonViewModel: PokemonViewModel = hiltViewModel(),
    pokemonScreenType: String? = null,
    typeRegionId: Int? = null,
) {
    val context = LocalContext.current

    val favouritePokemonIds by pokemonViewModel.favouritePokemonIds.collectAsState(listOf())

    val lazyPagingItems = when (pokemonScreenType) {
        PokemonScreenType.ALL_POKEMON.toString() -> pokemonViewModel.allPokemonPager.flow.collectAsLazyPagingItems()
        PokemonScreenType.TYPED_POKEMON.toString() -> pokemonViewModel.getTypedPokemon(typeRegionId!!).flow.collectAsLazyPagingItems()
        PokemonScreenType.REGIONAL_POKEMON.toString() -> pokemonViewModel.getRegionalPokemon(
            typeRegionId!!
        ).flow.collectAsLazyPagingItems()
        else -> throw RuntimeException("Invalid")
    }

    LazyPagingColumn(Modifier.fillMaxSize(), lazyPagingItems) { pokemon ->
        val inFavourites =
            if (favouritePokemonIds.isEmpty()) false else favouritePokemonIds.contains(pokemon.pokemonId)
        PokemonCard(
            pokemon = pokemon,
            onClick = {
                val intent = context.createIntent<DetailsActivity>()
                intent.putExtra(BundleKeys.POKEMON, pokemon)
                context.startActivity(intent)
            },
            inFavourites = inFavourites,
            onFavouriteClick = { pokemonViewModel.insertIntoFavourites(pokemon) },
            onUnFavouriteClick = { pokemonViewModel.deleteFromFavourites(pokemon) }
        )
    }
}
