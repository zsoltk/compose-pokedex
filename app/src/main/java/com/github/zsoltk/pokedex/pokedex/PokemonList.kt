package com.github.zsoltk.pokedex.pokedex

import androidx.compose.Composable
import androidx.compose.memo
import androidx.compose.unaryPlus
import androidx.ui.animation.Crossfade
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
import androidx.ui.layout.ExpandedHeight
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.Gravity
import androidx.ui.layout.Height
import androidx.ui.layout.Padding
import androidx.ui.layout.Spacing
import androidx.ui.layout.Stack
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.colorResource
import androidx.ui.res.imageResource
import androidx.ui.text.ParagraphStyle
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextAlign
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.R
import com.github.zsoltk.pokedex.common.AsyncState.Error
import com.github.zsoltk.pokedex.common.AsyncState.Initialised
import com.github.zsoltk.pokedex.common.AsyncState.Loading
import com.github.zsoltk.pokedex.common.AsyncState.Result
import com.github.zsoltk.pokedex.common.PokeBallBackground
import com.github.zsoltk.pokedex.common.PokeBallSmall
import com.github.zsoltk.pokedex.common.PokemonTypeLabels
import com.github.zsoltk.pokedex.common.RotateIndefinitely
import com.github.zsoltk.pokedex.common.TableRenderer
import com.github.zsoltk.pokedex.common.Title
import com.github.zsoltk.pokedex.common.TypeLabelMetrics.Companion.SMALL
import com.github.zsoltk.pokedex.common.observe
import com.github.zsoltk.pokedex.entity.Pokemon
import com.github.zsoltk.pokedex.entity.PokemonApi
import com.github.zsoltk.pokedex.entity.PokemonLiveData
import com.github.zsoltk.pokedex.entity.color
import com.github.zsoltk.pokedex.entity.pokemons
import com.github.zsoltk.pokedex.lightThemeColors

interface PokemonList {

    companion object {
        @Composable
        fun Content(onPokemonSelected: (Pokemon) -> Unit) {
            // You could lift this out to higher scope to survive this screen and avoid
            // loading every time. Kept here for demonstration purposes only.
            val liveData = +memo { PokemonLiveData() }
            val asyncState = +observe(liveData)

            Stack(modifier = ExpandedHeight wraps ExpandedWidth) {
                PokeBallBackground()

                expanded {
                    Crossfade(current = asyncState) {
                        when (it) {
                            is Initialised,
                            is Loading -> LoadingView()
                            is Error -> ErrorView(onRetryClicked = { liveData.reload() })
                            is Result -> ContentView(onPokemonSelected)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingView() {
    Container(expanded = true) {
        RotateIndefinitely(durationPerRotation = 400) {
            Container(width = 50.dp, height = 50.dp) {
                PokeBallSmall(tint = +colorResource(R.color.poke_light_red))
            }
        }
    }
}

@Composable
private fun ErrorView(onRetryClicked: () -> Unit) {
    val errorRatio = "%.0f".format(PokemonApi.randomFailureChance * 100)

    Container(expanded = true) {
        Column {
            Text(
                text = "There's a $errorRatio% chance of a simulated error.\nNow it happened.",
                style = (+MaterialTheme.typography()).body1.copy(
                    color = +colorResource(R.color.poke_red)
                ),
                paragraphStyle = ParagraphStyle(textAlign = TextAlign.Center),
                modifier = Spacing(bottom = 16.dp)
            )
            Button(
                modifier = Gravity.Center,
                text = "Retry",
                onClick = onRetryClicked
            )
        }
    }
}

@Composable
private fun ContentView(onPokemonSelected: (Pokemon) -> Unit) {
    Container(expanded = true) {
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
                PokeBallSmall(
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
