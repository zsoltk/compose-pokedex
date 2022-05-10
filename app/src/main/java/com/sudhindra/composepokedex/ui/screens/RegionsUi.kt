package com.sudhindra.composepokedex.ui.screens

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sudhindra.composepokedex.ui.components.Placeholder
import com.sudhindra.composepokedex.viemodel.RegionsScreenState
import com.sudhindra.composepokedex.viemodel.RegionsViewModel

@Composable
fun RegionsUi(
    viewModel: RegionsViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    LaunchedEffect(Unit) {
        viewModel.getRegions()
    }

    Crossfade(viewModel.regionsScreenState.collectAsState().value) { state ->
        when (state) {
            RegionsScreenState.Loading -> Placeholder(Modifier.fillMaxSize())
            is RegionsScreenState.ShowingData -> Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 20.dp, horizontal = 40.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically)
            ) {
                state.regions.forEach { region ->
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = { navHostController.navigate("pokemon/${PokemonScreenType.REGIONAL_POKEMON}/${region.id}") }
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 4.5.dp),
                            text = region.name,
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }
            is RegionsScreenState.Error -> Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = state.msg)
            }
        }
    }
}
