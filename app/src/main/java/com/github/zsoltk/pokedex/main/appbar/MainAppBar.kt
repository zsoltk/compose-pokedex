package com.github.zsoltk.pokedex.main.appbar

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.layout.Column
import androidx.ui.layout.HeightSpacer
import androidx.ui.layout.Padding
import androidx.ui.layout.Spacing
import androidx.ui.material.MaterialTheme
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.main.menu.Menu

@Composable
fun MainAppBar() {
    val colors = +MaterialTheme.colors()
    val typography = +MaterialTheme.typography()

    LargeAppBar(background = PokeBallLarge()) {
        Padding(padding = 16.dp) {
            Column {
                Text(
                    text = "What Pokémon\nare you looking for?",
                    style = typography.h4.copy(
                        color = colors.onSurface,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Spacing(
                        top = 64.dp,
                        bottom = 24.dp
                    )
                )
                RoundedSearchBar()
                HeightSpacer(height = 16.dp)
                Menu()
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainAppBar() {
    MainAppBar()
}


