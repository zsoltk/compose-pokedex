package com.github.zsoltk.pokedex.common

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.dp
import androidx.ui.layout.Container
import androidx.ui.layout.StackChildren
import androidx.ui.res.colorResource
import com.github.zsoltk.pokedex.R

@Composable
fun StackChildren.PokeBallBackground() {
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
            PokeBallLarge(
                +colorResource(
                    R.color.grey_100
                )
            )
        }
    }
}
