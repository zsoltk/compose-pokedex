package com.sudhindra.composepokedex.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.sudhindra.composepokedex.activities.DetailsActivity
import com.sudhindra.composepokedex.constants.BundleKeys
import com.sudhindra.composepokedex.ui.components.Center
import com.sudhindra.composepokedex.ui.components.PokemonCard
import com.sudhindra.composepokedex.utils.createIntent
import com.sudhindra.composepokedex.viemodel.FavouritesViewModel

// TODO: 5/18/2021 "No Pokemon" Text shows in the beginning for a split second
@Composable
fun FavouritesUi(
    viewModel: FavouritesViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val favourites by viewModel.favouritePokemon.collectAsState(listOf())
    LazyColumn {
        item {
            if (favourites.isEmpty()) {
                Center(Modifier.fillParentMaxSize()) {
                    Text(text = "No Favourites")
                }
            }
        }
        items(favourites) { favourite ->
            PokemonCard(
                pokemon = favourite.pokemon,
                onClick = {
                    val intent = context.createIntent<DetailsActivity>()
                    intent.putExtra(BundleKeys.POKEMON, favourite.pokemon)
                    context.startActivity(intent)
                },
                inFavourites = true,
                onFavouriteClick = {},
                onUnFavouriteClick = { viewModel.deleteFromFavourites(favourite) }
            )
        }
    }
}
