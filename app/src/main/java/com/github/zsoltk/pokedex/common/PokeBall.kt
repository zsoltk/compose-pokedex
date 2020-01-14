package com.github.zsoltk.pokedex.common

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Opacity
import androidx.ui.core.dp
import androidx.ui.foundation.DrawImage
import androidx.ui.graphics.Color
import androidx.ui.layout.Container
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.R

@Composable
fun PokeBallLarge(tint: Color, opacity: Float = 1f) {
    PokeBall(tint, opacity, R.drawable.pokeball)
}

@Composable
fun PokeBallSmall(tint: Color, opacity: Float = 1f) {
    PokeBall(tint, opacity, R.drawable.pokeball_s)
}

@Composable
private fun PokeBall(tint: Color, opacity: Float, imageResId: Int) {
    Opacity(opacity = opacity) {
        DrawImage(
            image = +imageResource(imageResId),
            tint = tint
        )
    }
}

@Preview
@Composable
fun PreviewPokeBall() {
    Container(width = 100.dp, height = 100.dp) {
        PokeBall(tint = Color.Black, opacity = 1f, imageResId = R.drawable.pokeball)
    }
}
