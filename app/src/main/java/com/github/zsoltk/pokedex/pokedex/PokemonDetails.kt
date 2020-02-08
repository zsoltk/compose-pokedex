package com.github.zsoltk.pokedex.pokedex

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Opacity
import androidx.ui.core.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutGravity
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutSize
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Padding
import androidx.ui.layout.Row
import androidx.ui.layout.Spacer
import androidx.ui.layout.Stack
import androidx.ui.layout.StackScope
import androidx.ui.material.Tab
import androidx.ui.material.TabRow
import androidx.ui.material.surface.Surface
import androidx.ui.res.colorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.github.zsoltk.pokedex.R
import com.github.zsoltk.pokedex.appFontFamily
import com.github.zsoltk.pokedex.common.LoadImage
import com.github.zsoltk.pokedex.common.PokeBallLarge
import com.github.zsoltk.pokedex.common.PokemonTypeLabels
import com.github.zsoltk.pokedex.common.Rotate
import com.github.zsoltk.pokedex.common.RotateIndefinitely
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
            Surface(color = colorResource(pokemon.color())) {
                Stack {
                    RoundedRectangleDecoration()
                    DottedDecoration()
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

private fun StackScope.RoundedRectangleDecoration() {
    Container(
        modifier = LayoutGravity.TopLeft + LayoutPadding(
            top = (-50).dp,
            left = (-60).dp
        )
    ) {
        Rotate(-20f) {
            Surface(color = Color(0x22FFFFFF), shape = RoundedCornerShape(32.dp)) {
                Spacer(modifier = LayoutWidth(150.dp) + LayoutHeight(150.dp))
            }
        }
    }
}

private fun StackScope.DottedDecoration() {
    Container(
        modifier = LayoutGravity.TopRight + LayoutPadding(
            top = 4.dp,
            right = 100.dp
        ),
        width = 63.dp,
        height = 34.dp
    ) {
        Opacity(opacity = 0.3f) {
            LoadImage(imageResId = R.drawable.dotted)
        }
    }
}

private fun StackScope.RotatingPokeBall() {
    Container(
        modifier = LayoutGravity.TopCenter +
            LayoutPadding(top = 140.dp) +
            LayoutSize(200.dp)
    ) {
        RotateIndefinitely(durationPerRotation = 4000) {
            PokeBallLarge(
                tint = colorResource(R.color.grey_100),
                opacity = 0.25f
            )
        }
    }
}

private fun StackScope.HeaderRight(pokemon: Pokemon) {
    Container(
        modifier = LayoutGravity.TopRight +
            LayoutPadding(top = 52.dp) +
            LayoutPadding(32.dp)
    ) {
        Column {
            Text(
                modifier = LayoutGravity.End,
                text = pokemon.id ?: "",
                style = TextStyle(
                    fontFamily = appFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            )
            Spacer(modifier = LayoutHeight(6.dp))
            Text(
                modifier = LayoutGravity.End,
                text = pokemon.category ?: "",
                style = TextStyle(
                    fontFamily = appFontFamily,
                    fontSize = 12.sp,
                    color = Color.White
                )
            )
        }
    }
}

private fun StackScope.HeaderLeft(pokemon: Pokemon) {
    Container(
        modifier = LayoutGravity.TopLeft +
            LayoutPadding(top = 40.dp) +
            LayoutPadding(32.dp)
    ) {
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

private enum class Sections(val title: String) {
    About("About"),
    BaseStats("Base stats"),
    Evolution("Evolution"),
    Moves("Moves")
}

private fun StackScope.CardContent(pokemon: Pokemon) {
    Container(
        modifier = LayoutGravity.TopCenter +
            LayoutPadding(top = 300.dp)
    ) {
        Surface(shape = RoundedCornerShape(topLeft = 32.dp, topRight = 32.dp)) {
            Column(modifier = LayoutWidth.Fill + LayoutHeight.Fill) {

                Spacer(modifier = LayoutHeight(32.dp))

                val sectionTitles = Sections.values().map { it.title }
                var section by state { Sections.BaseStats }
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

private fun StackScope.Image(pokemon: Pokemon) {
    pokemon.image?.let { image ->
        Container(
            modifier = LayoutGravity.TopCenter +
                LayoutPadding(top = 140.dp) +
                LayoutSize(200.dp)
        ) {
            LoadImage(image)
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
