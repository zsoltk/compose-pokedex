package com.sudhindra.composepokedex.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.sudhindra.composepokedex.ui.components.PokedexItemCard
import com.sudhindra.composepokedex.ui.paging.LazyPagingColumn
import com.sudhindra.composepokedex.viemodel.items.ItemsViewModel

@Composable
fun ItemsUi(itemsViewModel: ItemsViewModel = hiltViewModel()) {
    val lazyPagingItems = itemsViewModel.itemsPager.flow.collectAsLazyPagingItems()

    LazyPagingColumn(Modifier.fillMaxSize(), lazyPagingItems) { item ->
        PokedexItemCard(
            id = item.id,
            name = item.name,
            spriteUrl = item.itemSprite,
            onClick = {}
        )
    }
}
