package com.github.zsoltk.pokedex.common

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.graphics.Color
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.sp
import com.github.zsoltk.pokedex.appFontFamily

@Composable
fun Title(text: String, color: Color, modifier: Modifier = Modifier.None) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = appFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = color
        ),
        modifier = modifier
    )
}
