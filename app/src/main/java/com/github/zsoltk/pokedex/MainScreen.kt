package com.github.zsoltk.pokedex

import androidx.compose.Composable
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.main.appbar.MainAppBar
import com.github.zsoltk.pokedex.main.news.NewsSection

@Composable
fun MainScreen() {
    VerticalScroller {
        Column {
            MainAppBar()
            Padding(padding = 32.dp) {
                NewsSection()
            }
        }
    }
}


@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
