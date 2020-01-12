package com.github.zsoltk.pokedex.home.appbar

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.dp
import androidx.ui.layout.Column
import androidx.ui.layout.HeightSpacer
import androidx.ui.layout.Padding
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.common.Title
import com.github.zsoltk.pokedex.home.Home
import com.github.zsoltk.pokedex.home.appbar.elements.LargeAppBar
import com.github.zsoltk.pokedex.home.appbar.elements.Menu
import com.github.zsoltk.pokedex.home.appbar.elements.RoundedSearchBar
import com.github.zsoltk.pokedex.common.PokeBallBackground

@Composable
fun MainAppBar(onMenuItemSelected: (Home.MenuItem) -> Unit) {
    val colors = +MaterialTheme.colors()

    LargeAppBar(background = { PokeBallBackground() }) {
        Padding(
            top = 32.dp,
            left = 32.dp,
            right = 32.dp,
            bottom = 16.dp
        ) {
            Column {
                Title(
                    text = "What Pokémon\nare you looking for?",
                    color = colors.onSurface
                )
                RoundedSearchBar()
                HeightSpacer(height = 32.dp)
                Menu(onMenuItemSelected)
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainAppBar() {
    MainAppBar(onMenuItemSelected = {})
}


