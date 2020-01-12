package com.github.zsoltk.pokedex.common

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.graphics.Color
import androidx.ui.layout.Spacing
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight

@Composable
fun Title(text: String, color: Color) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = FontFamily("Roboto"),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = color
        ),
        modifier = Spacing(
            top = 64.dp,
            bottom = 24.dp
        )
    )
}
