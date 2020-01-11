package com.github.zsoltk.pokedex.main.appbar

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.HeightSpacer
import androidx.ui.layout.Padding
import com.github.zsoltk.pokedex.R
import androidx.ui.layout.Spacing
import androidx.ui.layout.StackChildren
import androidx.ui.material.MaterialTheme
import androidx.ui.res.colorResource
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.main.appbar.elements.LargeAppBar
import com.github.zsoltk.pokedex.main.appbar.elements.Menu
import com.github.zsoltk.pokedex.main.appbar.elements.RoundedSearchBar
import com.github.zsoltk.pokedex.main.pokeball.PokeBall

@Composable
fun MainAppBar() {
    LargeAppBar(background = PokeBallBg()) {
        Padding(
            top = 32.dp,
            left = 32.dp,
            right = 32.dp,
            bottom = 16.dp
        ) {
            Column {
                Title(text = "What Pokémon\nare you looking for?")
                RoundedSearchBar()
                HeightSpacer(height = 32.dp)
                Menu()
            }
        }
    }
}

@Composable
private fun Title(text: String) {
    val colors = +MaterialTheme.colors()
    val typography = +MaterialTheme.typography()

    Text(
        text = text,
        style = typography.h4.copy(
            color = colors.onSurface,
            fontWeight = FontWeight.Bold
        ),
        modifier = Spacing(
            top = 64.dp,
            bottom = 24.dp
        )
    )
}

@Composable
private fun PokeBallBg(): StackChildren.() -> Unit = {
    positioned(
        topInset =(-70).dp,
        rightInset = (-90).dp
    ) {
        Container(
            width = 240.dp,
            height = 240.dp,
            expanded = true,
            alignment = Alignment.TopRight
        ) {
            PokeBall(+colorResource(R.color.lighterGrey))
        }
    }
}

@Preview
@Composable
fun PreviewMainAppBar() {
    MainAppBar()
}


