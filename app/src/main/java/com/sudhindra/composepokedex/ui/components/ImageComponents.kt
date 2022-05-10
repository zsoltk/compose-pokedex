package com.sudhindra.composepokedex.ui.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.coil.rememberCoilPainter
import com.sudhindra.composepokedex.models.pokemon.Pokemon

@Composable
fun PokemonImage(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onDrawableReady: (Drawable) -> Unit,
    fadeIn: Boolean,
    contentDescription: String?
) {
    Image(
        modifier = modifier,
        // TODO: 5/24/2021 Palette Generation from Drawable is failing from Accompanist Version 0.8.0+ onwards
        painter = rememberCoilPainter(
            request = pokemon.getPokemonSprite(),
            requestBuilder = {
                memoryCacheKey(pokemon.pokemonId.toString())
                target { onDrawableReady(it) }
            },
            fadeIn = fadeIn
        ),
        contentDescription = contentDescription
    )
}
