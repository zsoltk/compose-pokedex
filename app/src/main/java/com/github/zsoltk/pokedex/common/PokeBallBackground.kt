package com.github.zsoltk.pokedex.common

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutOffset
import androidx.ui.res.colorResource
import androidx.ui.unit.dp
import com.github.zsoltk.pokedex.R

@Composable
fun PokeBallBackground() {
    Container(
        alignment = Alignment.TopEnd,
        modifier = LayoutOffset(x = 90.dp, y = (-70).dp),
        expanded = true
    ) {
        Container(
            width = 240.dp,
            height = 240.dp,
            expanded = true,
            alignment = Alignment.TopEnd
        ) {
            PokeBallLarge(
                colorResource(
                    R.color.grey_100
                )
            )
        }
    }
}
