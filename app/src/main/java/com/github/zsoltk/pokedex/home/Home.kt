package com.github.zsoltk.pokedex.home

import androidx.compose.Composable
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
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
        object Pokedex : MenuItem("Pokedex", R.color.teal)
        object Moves : MenuItem("Moves", R.color.red)
        object Abilities : MenuItem("Abilities", R.color.lightBlue)
        object Items : MenuItem("Items", R.color.yellow)
        object Locations : MenuItem("Locations", R.color.purple)
        object TypeCharts : MenuItem("Type charts", R.color.brown)
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
