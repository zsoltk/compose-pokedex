package com.github.zsoltk.pokedex.pokedex

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Opacity
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.Height
import androidx.ui.layout.Padding
import androidx.ui.layout.Spacing
import androidx.ui.layout.Stack
import androidx.ui.layout.StackChildren
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.colorResource
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.R
import com.github.zsoltk.pokedex.common.PokeBall
import com.github.zsoltk.pokedex.common.PokeBallBackground
import com.github.zsoltk.pokedex.common.PokemonTypeLabels
import com.github.zsoltk.pokedex.common.TableRenderer
import com.github.zsoltk.pokedex.common.Title
import com.github.zsoltk.pokedex.common.TypeLabelMetrics.Companion.SMALL
import com.github.zsoltk.pokedex.entity.Pokemon
import com.github.zsoltk.pokedex.entity.color
import com.github.zsoltk.pokedex.entity.pokemons
import com.github.zsoltk.pokedex.lightThemeColors

interface PokemonList {

    companion object {
        @Composable
        fun Content(onPokemonSelected: (Pokemon) -> Unit) {
            Surface {
                Stack {
                    PokeBallBackground()
                    PokedexContent(onPokemonSelected)
                }
            }
        }
    }
}

@Composable
private fun StackChildren.PokedexContent(onPokemonSelected: (Pokemon) -> Unit) {
    aligned(Alignment.TopLeft) {
        VerticalScroller {
            Padding(padding = 32.dp) {
                Column {
                    Title(
                        text = "Pokedex",
                        color = (+MaterialTheme.colors()).onSurface,
                        modifier = Spacing(
                            top = 64.dp,
                            bottom = 24.dp
                        )
                    )
                    TableRenderer(cols = 2, cellSpacing = 4.dp, items = pokemons) { cell ->
                        PokeDexCard(cell.item, onPokemonSelected)
                    }
                }
            }
        }
    }
}

@Composable
fun PokeDexCard(
    pokemon: Pokemon,
    onPokemonSelected: (Pokemon) -> Unit
) {
    Surface(
        color = +colorResource(pokemon.color()),
        shape = RoundedCornerShape(16.dp)
    ) {
        Ripple(bounded = true) {
            Clickable(onClick = { onPokemonSelected(pokemon) }) {
                PokeDexCardContent(pokemon)
            }
        }
    }
}

@Composable
private fun PokeDexCardContent(pokemon: Pokemon) {
    Stack(
        modifier = Height(120.dp) wraps ExpandedWidth
    ) {
        positioned(topInset = 8.dp, leftInset = 12.dp) {
            Column {
                PokemonName(pokemon.name)
                PokemonTypeLabels(pokemon.typeOfPokemon, SMALL)
            }
        }

        positioned(topInset = 10.dp, rightInset = 8.dp) {
            PokemonId(pokemon.id)
        }

        positioned(bottomInset = (-10).dp, rightInset = (-5).dp) {
            Container(width = 96.dp, height = 96.dp) {
                PokeBall(
                    Color.White,
                    0.25f
                )
            }
        }

        pokemon.image?.let { image ->
            positioned(bottomInset = (8).dp, rightInset = (8).dp) {
                Container(width = 72.dp, height = 72.dp) {
                    DrawImage(image = +imageResource(image))
                }
            }
        }
    }
}

@Composable
private fun PokemonName(text: String?) {
    Text(
        text = text ?: "",
        style = TextStyle(
            fontFamily = FontFamily("Roboto"),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = +colorResource(R.color.white_1000)
        ),
        modifier = Spacing(bottom = 8.dp)
    )
}

@Composable
private fun PokemonId(text: String?) {
    Opacity(opacity = 0.1f) {
        Text(
            text = text ?: "",
            style = TextStyle(
                fontFamily = FontFamily("Roboto"),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        )
    }
}

@Preview
@Composable
private fun PreviewPokemonCard() {
    MaterialTheme(lightThemeColors) {
        Container(width = 640.dp) {
            Pokedex.Content()
        }
    }
}
