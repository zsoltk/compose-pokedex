package com.github.zsoltk.pokedex.pokedex

import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.ExpandedHeight
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.Gravity
import androidx.ui.layout.HeightSpacer
import androidx.ui.layout.Padding
import androidx.ui.layout.Row
import androidx.ui.layout.Stack
import androidx.ui.layout.StackChildren
import androidx.ui.material.Tab
import androidx.ui.material.TabRow
import androidx.ui.material.surface.Surface
import androidx.ui.res.colorResource
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.R
import com.github.zsoltk.pokedex.common.PokeBallLarge
import com.github.zsoltk.pokedex.common.PokemonTypeLabels
import com.github.zsoltk.pokedex.common.Rotate
import com.github.zsoltk.pokedex.common.Title
import com.github.zsoltk.pokedex.common.TypeLabelMetrics.Companion.MEDIUM
import com.github.zsoltk.pokedex.entity.Pokemon
import com.github.zsoltk.pokedex.entity.color
import com.github.zsoltk.pokedex.entity.pokemons
import com.github.zsoltk.pokedex.pokedex.section.AboutSection
import com.github.zsoltk.pokedex.pokedex.section.BaseStatsSection
import com.github.zsoltk.pokedex.pokedex.section.EvolutionSection
import com.github.zsoltk.pokedex.pokedex.section.MovesSection

interface PokemonDetails {

    companion object {
        @Composable
        fun Content(pokemon: Pokemon) {
            Surface(color = +colorResource(pokemon.color())) {
                Stack {
                    RotatingPokeBall()
                    HeaderLeft(pokemon)
                    HeaderRight(pokemon)
                    CardContent(pokemon)
                    Image(pokemon)
                }
            }
        }
    }
}

private fun StackChildren.RotatingPokeBall() {
    positioned(topInset = 140.dp) {
        Container(width = 200.dp, height = 200.dp) {
            Rotate(duration = 4000) {
                PokeBallLarge(
                    tint = +colorResource(R.color.grey_100),
                    opacity = 0.25f
                )
            }
        }
    }
}

private fun StackChildren.HeaderRight(pokemon: Pokemon) {
    positioned(topInset = 52.dp, rightInset = 0.dp) {
        Padding(32.dp) {
            Column {
                Text(
                    modifier = Gravity.End,
                    text = pokemon.id ?: "",
                    style = TextStyle(
                        fontFamily = FontFamily("Roboto"),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                )
                HeightSpacer(height = 6.dp)
                Text(
                    modifier = Gravity.End,
                    text = pokemon.category ?: "",
                    style = TextStyle(
                        fontFamily = FontFamily("Roboto"),
                        fontSize = 12.sp,
                        color = Color.White
                    )
                )
            }
        }
    }
}

private fun StackChildren.HeaderLeft(pokemon: Pokemon) {
    positioned(topInset = 40.dp, leftInset = 0.dp) {
        Padding(32.dp) {
            Column {
                Title(
                    text = pokemon.name ?: "",
                    color = Color.White
                )

                pokemon.typeOfPokemon?.let {
                    Row {
                        PokemonTypeLabels(it, MEDIUM)
                    }
                }
            }
        }
    }
}

private enum class Sections(val title: String) {
    About("About"),
    BaseStats("Base stats"),
    Evolution("Evolution"),
    Moves("Moves")
}

private fun StackChildren.CardContent(pokemon: Pokemon) {
    positioned(topInset = 300.dp) {
        Surface(shape = RoundedCornerShape(topLeft = 32.dp, topRight = 32.dp)) {
            Column(modifier = ExpandedWidth wraps ExpandedHeight) {

                HeightSpacer(height = 32.dp)

                val sectionTitles = Sections.values().map { it.title }
                var section by +state { Sections.BaseStats }
                TabRow(items = sectionTitles, selectedIndex = section.ordinal) { index, text ->
                    Tab(text = text, selected = section.ordinal == index) {
                        section = Sections.values()[index]
                    }
                }

                Padding(padding = 24.dp) {
                    when (section) {
                        Sections.About -> AboutSection(pokemon)
                        Sections.BaseStats -> BaseStatsSection(pokemon)
                        Sections.Evolution -> EvolutionSection(pokemon)
                        Sections.Moves -> MovesSection(pokemon)
                    }
                }
            }
        }
    }
}

private fun StackChildren.Image(pokemon: Pokemon) {
    pokemon.image?.let { image ->
        positioned(topInset = 140.dp) {
            Container(width = 200.dp, height = 200.dp) {
                DrawImage(+imageResource(image))
            }
        }
    }
}


@Preview
@Composable
private fun PreviewPokemonDetails() {
    Container(expanded = true) {
        PokemonDetails.Content(pokemons.first())
    }
}
