package com.sudhindra.composepokedex.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.sudhindra.composepokedex.ui.components.PokedexItemCard
import com.sudhindra.composepokedex.ui.paging.LazyPagingColumn
import com.sudhindra.composepokedex.viemodel.locations.LocationsViewModel

@Composable
fun LocationsUi(locationsViewModel: LocationsViewModel = hiltViewModel()) {
    val lazyPagingItems = locationsViewModel.locationsPager.flow.collectAsLazyPagingItems()

    LazyPagingColumn(Modifier.fillMaxSize(), lazyPagingItems) { pokemon ->
        PokedexItemCard(
            id = pokemon.id,
            name = pokemon.name,
            onClick = {}
        )
    }
}
