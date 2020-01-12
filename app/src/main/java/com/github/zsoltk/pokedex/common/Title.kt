package com.github.zsoltk.pokedex.common

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.graphics.Color
import androidx.ui.layout.Spacing
import androidx.ui.material.MaterialTheme
import androidx.ui.text.font.FontWeight

@Composable
fun Title(text: String, color: Color) {
    val typography = +MaterialTheme.typography()

    Text(
        text = text,
        style = typography.h4.copy(
            color = color,
            fontWeight = FontWeight.Bold
        ),
        modifier = Spacing(
            top = 64.dp,
            bottom = 24.dp
        )
    )
}
