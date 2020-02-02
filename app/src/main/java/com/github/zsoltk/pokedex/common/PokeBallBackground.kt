package com.github.zsoltk.pokedex.common

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.layout.Container
import androidx.ui.layout.EdgeInsets
import androidx.ui.res.colorResource
import androidx.ui.unit.dp
import com.github.zsoltk.pokedex.R

@Composable
fun PokeBallBackground() {
    Container(
        alignment = Alignment.TopRight,
        padding = EdgeInsets(top = (-70).dp, right = (-90).dp),
        expanded = true
    ) {
        Container(
            width = 240.dp,
            height = 240.dp,
            expanded = true,
            alignment = Alignment.TopRight
        ) {
            PokeBallLarge(
                colorResource(
                    R.color.grey_100
                )
            )
        }
    }
}
