package com.github.zsoltk.pokedex.main.appbar

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.layout.Column
import androidx.ui.layout.HeightSpacer
import androidx.ui.material.MaterialTheme
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview

@Preview
@Composable
fun MainAppBar() {
    val colors = +MaterialTheme.colors()
    val typography = +MaterialTheme.typography()

    LargeAppBar(background = PokeBallLarge()) {
        Column {
            Text(
                text = "What Pokémon\nare you looking for?",
                style = typography.h4.copy(
                    color = colors.onPrimary,
                    fontWeight = FontWeight.Bold
                )
            )
            HeightSpacer(height = 16.dp)
            RoundedSearchBar()
        }
    }
}
