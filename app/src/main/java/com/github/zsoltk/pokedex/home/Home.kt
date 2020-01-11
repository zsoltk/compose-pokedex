package com.github.zsoltk.pokedex.home

import androidx.compose.Composable
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.home.appbar.MainAppBar
import com.github.zsoltk.pokedex.home.news.NewsSection

interface Home {

    sealed class MenuItem {
        object Pokedex : MenuItem()
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
