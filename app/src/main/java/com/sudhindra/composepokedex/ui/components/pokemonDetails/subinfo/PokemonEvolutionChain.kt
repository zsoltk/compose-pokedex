package com.sudhindra.composepokedex.ui.components.pokemonDetails.subinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.sudhindra.composepokedex.R
import com.sudhindra.composepokedex.models.pokemon.EvolutionChain
import com.sudhindra.composepokedex.models.pokemon.PokemonSpecies
import com.sudhindra.composepokedex.ui.components.Placeholder
import com.sudhindra.composepokedex.utils.splitAndCapitalise
import com.sudhindra.composepokedex.viemodel.details.EvolutionChainState

@Composable
fun PokemonEvolutionChain(
    state: EvolutionChainState,
    onPokemonClick: (PokemonSpecies) -> Unit
) {
    Card(shape = RoundedCornerShape(15.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state) {
                EvolutionChainState.Loading -> Placeholder(Modifier.fillMaxSize())
                is EvolutionChainState.Error -> Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = state.msg)
                }
                is EvolutionChainState.Success -> {
                    if (state.evolutionChain.chain != null) {
                        ChainLink(
                            state.evolutionChain.chain,
                            onPokemonClick = onPokemonClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ChainLink(
    chainLink: EvolutionChain.ChainLink,
    onPokemonClick: (PokemonSpecies) -> Unit
) {
    Image(
        modifier = Modifier
            .size(140.dp)
            .clickable(
                MutableInteractionSource(),
                indication = null,
                onClick = { onPokemonClick(chainLink.species) }
            ),
        painter = rememberCoilPainter(
            request = chainLink.species.pokemonSprite,
            fadeIn = true
        ),
        contentDescription = "Pokemon Sprite"
    )
    Text(text = chainLink.species.name.splitAndCapitalise())
    if (chainLink.evolves_to.isNotEmpty()) {
        val nextLink = chainLink.evolves_to.first()
        if (nextLink != null) {
            Icon(painter = painterResource(R.drawable.ic_arrow_down), contentDescription = "")
            ChainLink(chainLink = nextLink, onPokemonClick = onPokemonClick)
        }
    }
}
