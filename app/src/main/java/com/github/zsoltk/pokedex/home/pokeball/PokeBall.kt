package com.github.zsoltk.pokedex.home.pokeball

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Opacity
import androidx.ui.foundation.DrawImage
import androidx.ui.graphics.Color
import androidx.ui.res.imageResource
import com.github.zsoltk.pokedex.R

@Composable
fun PokeBall(tint: Color, opacity: Float = 1f) {
    Opacity(opacity = opacity) {
        DrawImage(
            image = +imageResource(R.drawable.pokeball),
            tint = tint
        )
    }
}
