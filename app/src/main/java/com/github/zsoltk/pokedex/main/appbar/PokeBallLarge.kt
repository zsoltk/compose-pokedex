package com.github.zsoltk.pokedex.main.appbar

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.dp
import androidx.ui.foundation.DrawImage
import androidx.ui.layout.Container
import androidx.ui.layout.StackChildren
import androidx.ui.res.colorResource
import androidx.ui.res.imageResource
import com.github.zsoltk.pokedex.R

@Composable
fun PokeBallLarge(): StackChildren.() -> Unit = {
    positioned(
        topInset =(-70).dp,
        rightInset = (-90).dp
    ) {
        Container(
            width = 240.dp,
            height = 240.dp,
            expanded = true,
            alignment = Alignment.TopRight
        ) {
            DrawImage(
                image = +imageResource(R.drawable.pokeball),
                tint = +colorResource(R.color.lighterGrey)
            )
        }
    }
}
