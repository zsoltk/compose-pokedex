package com.github.zsoltk.pokedex.pokedex

import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.animation.Crossfade
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.drawOpacity
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Image
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutGravity
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutOffset
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutSize
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Stack
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.Surface
import androidx.ui.res.colorResource
import androidx.ui.res.imageResource
import androidx.ui.text.AnnotatedString
import androidx.ui.text.ParagraphStyle
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextAlign
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.github.zsoltk.pokedex.R
import com.github.zsoltk.pokedex.appFontFamily
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
            val liveData = remember { PokemonLiveData() }
            val asyncState = observe(liveData)

            Stack(modifier = LayoutHeight.Fill + LayoutWidth.Fill) {
                Surface(color = MaterialTheme.colors().surface) {
                    PokeBallBackground()
                }

                Container(modifier = LayoutGravity.Stretch) {
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
                PokeBallSmall(tint = colorResource(R.color.poke_light_red))
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
                text = AnnotatedString(
                    text = "There's a $errorRatio% chance of a simulated error.\nNow it happened.",
                    paragraphStyle = ParagraphStyle(textAlign = TextAlign.Center)
                ),
                style = MaterialTheme.typography().body1.copy(
                    color = colorResource(R.color.poke_red)
                ),
                modifier = LayoutPadding(bottom = 16.dp)
            )
            Button(
                modifier = LayoutGravity.Center,
                onClick = onRetryClicked
            ) {
                Text("Retry")
            }
        }
    }
}

@Composable
private fun ContentView(onPokemonSelected: (Pokemon) -> Unit) {
    Container(alignment = Alignment.TopStart) {
        VerticalScroller {
            Column(LayoutPadding(32.dp)) {
                Title(
                    text = "Pokedex",
                    color = MaterialTheme.colors().onSurface,
                    modifier = LayoutPadding(
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

@Composable
fun PokeDexCard(
    pokemon: Pokemon,
    onPokemonSelected: (Pokemon) -> Unit
) {
    Surface(
        color = colorResource(pokemon.color()),
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
    Stack(modifier = LayoutHeight(120.dp) + LayoutWidth.Fill) {
        Column(modifier = LayoutGravity.TopStart + LayoutPadding(top = 8.dp, start = 12.dp)) {
            PokemonName(pokemon.name)
            PokemonTypeLabels(pokemon.typeOfPokemon, SMALL)
        }

        Container(LayoutGravity.TopEnd + LayoutPadding(top = 8.dp, end = 12.dp)) {
            PokemonId(pokemon.id)
        }

        Container(
            modifier = LayoutGravity.BottomEnd +
                LayoutOffset(x = 5.dp, y = 10.dp) +
                LayoutSize(96.dp)
        ) {
            PokeBallSmall(
                Color.White,
                0.25f
            )
        }

        pokemon.image?.let { image ->
            Container(
                modifier = LayoutGravity.BottomEnd +
                    LayoutPadding(bottom = 8.dp, end = 8.dp) +
                    LayoutSize(72.dp)
            ) {
                Image(image = imageResource(image))
            }
        }
    }
}

@Composable
private fun PokemonName(text: String?) {
    Text(
        text = text ?: "",
        style = TextStyle(
            fontFamily = appFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = colorResource(R.color.white_1000)
        ),
        modifier = LayoutPadding(bottom = 8.dp)
    )
}

@Composable
private fun PokemonId(text: String?) {
    Text(
        text = text ?: "",
        modifier = drawOpacity(0.1f),
        style = TextStyle(
            fontFamily = appFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    )
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
