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
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.Height
import androidx.ui.layout.HeightSpacer
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
import com.github.zsoltk.pokedex.common.TableRenderer
import com.github.zsoltk.pokedex.common.Title
import com.github.zsoltk.pokedex.entity.Pokemon
import com.github.zsoltk.pokedex.entity.color
import com.github.zsoltk.pokedex.entity.pokemons
import com.github.zsoltk.pokedex.lightThemeColors

interface Pokedex {

    companion object {
        @Composable
        fun Content() {
            Surface {
                Stack {
                    PokeBallBackground()
                    PokedexContent()
                }
            }

        }
    }
}

@Composable
private fun StackChildren.PokedexContent() {
    aligned(Alignment.TopLeft) {
        Padding(padding = 32.dp) {
            Column {
                Title(text = "Pokedex", color = (+MaterialTheme.colors()).onSurface)
                TableRenderer(cols = 2, cellSpacing = 4.dp, items = pokemons) { cell ->
                    Clickable(onClick = { }) {
                        PokeDexCard(cell.item)
                    }
                }
            }
        }
    }
}

@Composable
fun PokeDexCard(pokemon: Pokemon) {
    Surface(
        color = +colorResource(pokemon.color()),
        shape = RoundedCornerShape(16.dp)
    ) {
        Ripple(bounded = true) {
            Stack(
                modifier = Height(120.dp) wraps ExpandedWidth
            ) {
                positioned(topInset = 8.dp, leftInset = 12.dp) {
                    Column {
                        PokemonName(pokemon.name)
                        PokemonTypes(pokemon.typeofpokemon)
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
    }
}

@Composable
private fun PokemonName(text: String?) {
    val typography = +MaterialTheme.typography()

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

@Composable
private fun PokemonTypes(types: List<String>?) {
    Column {
        types?.forEach {
            Surface(
                color = Color(0x38FFFFFF),
                shape = RoundedCornerShape(24.dp)
            ) {
                TypeLabel(it)
            }
            HeightSpacer(height = 8.dp)
        }
    }
}

@Composable
private fun TypeLabel(text: String) {
    Padding(
        top = 3.dp,
        bottom = 3.dp,
        left = 8.dp,
        right = 8.dp
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = FontFamily("Roboto"),
                fontSize = 9.sp,
                color = +colorResource(R.color.white_1000)
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
