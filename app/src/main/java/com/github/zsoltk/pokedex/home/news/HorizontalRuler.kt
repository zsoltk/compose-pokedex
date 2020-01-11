package com.github.zsoltk.pokedex.home.news

import androidx.compose.Composable
import androidx.ui.core.Dp
import androidx.ui.core.dp
import androidx.ui.graphics.Color
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Container
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.Row
import androidx.ui.layout.WidthSpacer
import androidx.ui.material.surface.Surface

@Composable
fun HorizontalRuler(
    color: Color,
    width: Dp? = null,
    height: Dp? = 2.dp,
    centered: Boolean = true
) {
    Row(
        modifier = ExpandedWidth,
        arrangement = if (centered) Arrangement.Center else Arrangement.Begin
    ) {
        Surface(color = color) {
            Container(
                expanded = true,
                width = width,
                height = height
            ) {
                WidthSpacer(width = 1.dp)
            }
        }
    }
}
