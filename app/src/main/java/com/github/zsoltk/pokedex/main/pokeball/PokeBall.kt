package com.github.zsoltk.pokedex.main.pokeball

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Opacity
import androidx.ui.core.dp
import androidx.ui.foundation.DrawImage
import androidx.ui.graphics.Color
import androidx.ui.layout.Container
import androidx.ui.layout.StackChildren
import androidx.ui.res.colorResource
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
