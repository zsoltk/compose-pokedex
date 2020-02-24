package com.github.zsoltk.pokedex.home.appbar

import android.icu.text.CaseMap
import androidx.compose.Composable
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.Spacer
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.github.zsoltk.pokedex.common.PokeBallBackground
import com.github.zsoltk.pokedex.common.Title
import com.github.zsoltk.pokedex.home.Home
import com.github.zsoltk.pokedex.home.appbar.elements.LargeAppBar
import com.github.zsoltk.pokedex.home.appbar.elements.Menu
import com.github.zsoltk.pokedex.home.appbar.elements.RoundedSearchBar

@Composable
fun MainAppBar(onMenuItemSelected: (Home.MenuItem) -> Unit) {
    LargeAppBar(background = { PokeBallBackground() }) {
        Column(
            modifier = LayoutPadding(
                top = 32.dp,
                left = 32.dp,
                right = 32.dp,
                bottom = 16.dp
            )
        ) {
            Title(
                text = "What Pokémon\nare you looking for?",
                color = MaterialTheme.colors().onSurface,
                modifier = LayoutPadding(
                    top = 64.dp,
                    bottom = 24.dp
                )
            )
            RoundedSearchBar()
            Spacer(modifier = LayoutHeight(32.dp))
            Menu(onMenuItemSelected)
        }
    }
}

@Preview
@Composable
fun PreviewMainAppBar() {
    MainAppBar(onMenuItemSelected = {})
}


