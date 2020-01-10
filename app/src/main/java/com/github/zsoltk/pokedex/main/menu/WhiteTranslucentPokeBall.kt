package com.github.zsoltk.pokedex.main.menu

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Opacity
import androidx.ui.foundation.DrawImage
import androidx.ui.graphics.Color
import androidx.ui.res.imageResource
import com.github.zsoltk.pokedex.R

@Composable
fun WhiteTranslucentPokeBall() {
    Opacity(opacity = 0.15f) {
        DrawImage(
            image = +imageResource(R.drawable.pokeball),
            tint = Color.White
        )
    }
}
