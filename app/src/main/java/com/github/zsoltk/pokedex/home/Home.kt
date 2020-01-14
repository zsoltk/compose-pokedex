package com.github.zsoltk.pokedex.home

import androidx.compose.Composable
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.R
import com.github.zsoltk.pokedex.home.appbar.MainAppBar
import com.github.zsoltk.pokedex.home.news.NewsSection

interface Home {

    sealed class MenuItem(
        val label: String,
        val colorResId: Int
    ) {
        object Pokedex : MenuItem("Pokedex", R.color.poke_teal)
        object Moves : MenuItem("Moves", R.color.poke_red)
        object Abilities : MenuItem("Abilities", R.color.poke_light_blue)
        object Items : MenuItem("Items", R.color.poke_yellow)
        object Locations : MenuItem("Locations", R.color.poke_purple)
        object TypeCharts : MenuItem("Type charts", R.color.poke_brown)
    }

    companion object {
        @Composable
        fun Content(onMenuItemSelected: (MenuItem) -> Unit) {
            VerticalScroller {
                Column {
                    MainAppBar(onMenuItemSelected)
                    Padding(padding = 32.dp) {
                        NewsSection()
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewMainScreen() {
    Home.Content(onMenuItemSelected = {})
}
