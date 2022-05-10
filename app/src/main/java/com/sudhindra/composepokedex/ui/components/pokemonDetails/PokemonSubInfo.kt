package com.sudhindra.composepokedex.ui.components.pokemonDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sudhindra.composepokedex.models.pokemon.Pokemon
import com.sudhindra.composepokedex.models.pokemon.PokemonSpecies
import com.sudhindra.composepokedex.ui.components.pokemonDetails.subinfo.PokemonAboutSection
import com.sudhindra.composepokedex.ui.components.pokemonDetails.subinfo.PokemonEvolutionChain
import com.sudhindra.composepokedex.ui.components.pokemonDetails.subinfo.PokemonStatsSection
import com.sudhindra.composepokedex.viemodel.details.EvolutionChainState

private val subInfoSections = listOf(
    "About",
    "Stats",
    "Evolution Chain"
)

@Composable
fun PokemonSubInfo(
    pokemon: Pokemon,
    evolutionChainState: EvolutionChainState,
    onPokemonClick: (PokemonSpecies) -> Unit
) {
    var selectedSection by rememberSaveable { mutableStateOf(0) }
    ScrollableTabRow(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
        selectedTabIndex = selectedSection,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        subInfoSections.forEachIndexed { index, s ->
            Tab(
                selected = index == selectedSection,
                onClick = { selectedSection = index }) {
                Text(text = s, modifier = Modifier.padding(10.dp))
            }
        }
    }
    Box(
        Modifier.padding(start = 18.dp, end = 18.dp, bottom = 18.dp)
    ) {
        when (selectedSection) {
            0 -> PokemonAboutSection(pokemon)
            1 -> PokemonStatsSection(pokemon)
            2 -> PokemonEvolutionChain(evolutionChainState, onPokemonClick)
        }
    }
}
