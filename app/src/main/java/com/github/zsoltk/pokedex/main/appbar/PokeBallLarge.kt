package com.github.zsoltk.pokedex.main.appbar

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Opacity
import androidx.ui.core.dp
import androidx.ui.foundation.DrawImage
import androidx.ui.layout.Container
import androidx.ui.layout.StackChildren
import androidx.ui.res.imageResource
import com.github.zsoltk.pokedex.R

@Composable
fun PokeBallLarge(): StackChildren.() -> Unit = {
    positioned(
        topInset =(-90).dp,
        rightInset = (-90).dp
    ) {
        Container(
            width = 260.dp,
            height = 260.dp,
            expanded = true,
            alignment = Alignment.TopRight
        ) {
            Opacity(opacity = 0.1f) {
                DrawImage(
                    image = +imageResource(
                        R.drawable.pokeball
                    )
                )
            }
        }
    }
}
