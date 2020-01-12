package com.github.zsoltk.pokedex.pokedex.section

import androidx.compose.Composable
import androidx.ui.core.Text
import com.github.zsoltk.pokedex.entity.Pokemon

@Composable
fun AboutSection(pokemon: Pokemon) {
    pokemon.description?.let {
        Text(it)
    }
}
