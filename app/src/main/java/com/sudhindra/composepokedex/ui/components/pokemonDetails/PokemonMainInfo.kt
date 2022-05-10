package com.sudhindra.composepokedex.ui.components.pokemonDetails

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sudhindra.composepokedex.models.pokemon.Pokemon
import com.sudhindra.composepokedex.ui.components.PokemonImage
import com.sudhindra.composepokedex.ui.components.PokemonTypesChipGroup

@Composable
fun PokemonMainInfo(
    pokemon: Pokemon,
    onDrawableReady: (Drawable) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 45.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = pokemon.formattedName,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.size(6.dp))
            PokemonTypesChipGroup(pokemon.types)
        }
        Text(text = "#${pokemon.pokemonId}", style = MaterialTheme.typography.h6)
    }
    PokemonImage(
        Modifier.size(280.dp),
        pokemon = pokemon,
        onDrawableReady = onDrawableReady,
        fadeIn = true,
        contentDescription = "${pokemon.formattedName} Sprite"
    )
}
