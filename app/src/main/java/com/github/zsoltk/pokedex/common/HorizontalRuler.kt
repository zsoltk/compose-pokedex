package com.github.zsoltk.pokedex.common

import androidx.compose.Composable
import androidx.ui.graphics.Color
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Row
import androidx.ui.layout.Spacer
import androidx.ui.material.Surface
import androidx.ui.unit.Dp
import androidx.ui.unit.dp

@Composable
fun HorizontalRuler(
    color: Color,
    width: Dp? = null,
    height: Dp? = 2.dp,
    centered: Boolean = true
) {
    Row(
        modifier = LayoutWidth.Fill,
        arrangement = if (centered) Arrangement.Center else Arrangement.Start
    ) {
        Surface(color = color) {
            Container(
                expanded = true,
                width = width,
                height = height
            ) {
                Spacer(modifier = LayoutWidth(1.dp))
            }
        }
    }
}
