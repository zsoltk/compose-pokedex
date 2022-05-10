package com.sudhindra.composepokedex.ui.components.pokemonDetails

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.sudhindra.composepokedex.ui.components.BackButton

@Composable
fun DetailsAppBar(
    inFavourites: Boolean,
    onShareClick: () -> Unit,
    onFavouriteClick: () -> Unit,
    onUnFavouriteClick: () -> Unit
) {
    TopAppBar(
        title = {},
        // TODO: 4/6/2021 : Add Transparency Effect
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = {
            BackButton()
        },
        actions = {
            IconButton(onClick = if (!inFavourites) onFavouriteClick else onUnFavouriteClick) {
                Icon(
                    imageVector = if (!inFavourites) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                    contentDescription = "Back Button"
                )
            }
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Back Button"
                )
            }
        },
        elevation = 0.dp
    )
}
