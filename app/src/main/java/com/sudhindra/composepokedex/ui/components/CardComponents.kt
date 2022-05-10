package com.sudhindra.composepokedex.ui.components

import android.graphics.drawable.Drawable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.sudhindra.composepokedex.models.pokemon.Pokemon
import com.sudhindra.composepokedex.utils.generatePalette
import com.sudhindra.composepokedex.utils.sharePokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: () -> Unit,
    inFavourites: Boolean,
    onFavouriteClick: () -> Unit,
    onUnFavouriteClick: () -> Unit
) {
    var drawable: Drawable? by remember { mutableStateOf(null) }
    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember { mutableStateOf(defaultDominantColor) }
    val backgroundColor by animateColorAsState(dominantColor, animationSpec = tween(500))

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isDark = isSystemInDarkTheme()

    Card(
        Modifier.padding(10.dp),
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            Modifier
                .clickable { onClick() }
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PokemonImage(
                Modifier.size(80.dp),
                pokemon = pokemon,
                onDrawableReady = {
                    drawable = it
                    scope.launch(Dispatchers.IO) {
                        drawable?.generatePalette { palette ->
                            val swatch =
                                if (isDark) palette.darkMutedSwatch else palette.lightMutedSwatch
                            swatch?.rgb?.let { colorInt ->
                                dominantColor = Color(colorInt)
                            }
                        }
                    }
                },
                fadeIn = true,
                contentDescription = "${pokemon.formattedName} Sprite"
            )
            Column(
                Modifier.weight(2f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(text = "#${pokemon.pokemonId}", style = MaterialTheme.typography.h6)
                    Text(
                        text = pokemon.formattedName,
                        style = MaterialTheme.typography.h6,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                PokemonTypesChipGroup(pokemon.types)
            }
            Column {
                IconButton(onClick = if (!inFavourites) onFavouriteClick else onUnFavouriteClick) {
                    Icon(
                        imageVector = if (!inFavourites) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                        contentDescription = "Back Button"
                    )
                }
                IconButton(onClick = {
                    drawable?.let { context.sharePokemon(pokemon, it) }
                }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Back Button"
                    )
                }
            }
        }
    }
}

@Composable
fun PokedexItemCard(
    id: Int,
    name: String,
    spriteUrl: String? = null,
    onClick: () -> Unit
) {
    Card(
        Modifier.padding(10.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            Modifier
                .clickable { onClick() }
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement =
            if (spriteUrl != null) Arrangement.SpaceBetween else
                Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = id.toString(), style = MaterialTheme.typography.h6)
            Text(
                text = name, style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (spriteUrl != null)
                Image(
                    modifier = Modifier.size(50.dp),
                    painter = rememberCoilPainter(
                        request = spriteUrl,
                        fadeIn = true
                    ),
                    contentDescription = "Pokemon Sprite"
                )
        }
    }
}
