package com.sudhindra.composepokedex.ui.components.pokemonDetails.subinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudhindra.composepokedex.models.pokemon.Pokemon

@Composable
fun PokemonAboutSection(pokemon: Pokemon) {
    Card(shape = RoundedCornerShape(15.dp)) {
        Row(
            Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    PokemonAboutText(
                        label = "Height",
                        value = "${pokemon.height.toFloat() / 10} M"
                    )
                    PokemonAboutText(
                        label = "Weight",
                        value = "${pokemon.weight.toFloat() / 10} KG"
                    )
                }
                Spacer(Modifier.height(32.dp))
                PokemonAboutText(label = "Abilities", value = pokemon.abilitiesAsString())
            }
        }
    }
}

@Composable
private fun PokemonAboutText(
    label: String,
    value: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value, style = TextStyle(fontSize = 24.sp),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(6.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
        )
    }
}
