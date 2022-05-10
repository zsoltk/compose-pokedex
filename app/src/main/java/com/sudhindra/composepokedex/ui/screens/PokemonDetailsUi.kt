package com.sudhindra.composepokedex.ui.screens

import android.graphics.drawable.Drawable
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.sudhindra.composepokedex.models.pokemon.Pokemon
import com.sudhindra.composepokedex.ui.components.ProgressDialog
import com.sudhindra.composepokedex.ui.components.pokemonDetails.DetailsAppBar
import com.sudhindra.composepokedex.ui.components.pokemonDetails.PokemonMainInfo
import com.sudhindra.composepokedex.ui.components.pokemonDetails.PokemonSubInfo
import com.sudhindra.composepokedex.ui.components.toast
import com.sudhindra.composepokedex.utils.generatePalette
import com.sudhindra.composepokedex.utils.sharePokemon
import com.sudhindra.composepokedex.viemodel.details.DetailsViewModel
import com.sudhindra.composepokedex.viemodel.details.NewPokemonState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PokemonDetailsUi(
    viewModel: DetailsViewModel,
    onNewPokemonSuccess: (Pokemon) -> Unit
) {
    var drawable: Drawable? by remember { mutableStateOf(null) }
    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember { mutableStateOf(defaultDominantColor) }
    val backgroundColor by animateColorAsState(dominantColor /*animationSpec = tween(500)*/)

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isDark = isSystemInDarkTheme()

    val pokemon = viewModel.pokemon
    val inFavourites by viewModel.inFavourites.collectAsState(false)

    LaunchedEffect(Unit) {
        viewModel.getEvolutionChain(pokemon.pokemonId)
    }

    Scaffold(
        topBar = {
            DetailsAppBar(
                inFavourites = inFavourites,
                onShareClick = {
                    drawable?.let { context.sharePokemon(pokemon, it) }
                },
                onFavouriteClick = { viewModel.insertIntoFavourites() },
                onUnFavouriteClick = { viewModel.deleteFromFavourites() }
            )
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.size(0.dp))
            PokemonMainInfo(
                pokemon,
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
                }
            )
            PokemonSubInfo(
                pokemon = pokemon,
                evolutionChainState = viewModel.evolutionChainState.collectAsState().value,
                onPokemonClick = {
                    if (pokemon.pokemonId != it.pokemonId)
                        viewModel.getNewPokemonInfo(it)
                }
            )
        }

        when (val state = viewModel.newPokemonState.collectAsState().value) {
            NewPokemonState.Loading -> ProgressDialog(
                onDismissRequest = { },
                properties = DialogProperties(false, false)
            ) {
                Text(text = "Loading...")
            }
            is NewPokemonState.Error -> toast(state.msg)
            is NewPokemonState.Success -> onNewPokemonSuccess(state.pokemon)
        }
    }
}
