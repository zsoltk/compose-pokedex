package com.github.zsoltk.pokedex

import android.graphics.Paint
import android.graphics.Rect
import androidx.compose.Composable
import androidx.compose.memo
import androidx.compose.unaryPlus
import androidx.ui.core.Draw
import androidx.ui.graphics.Color
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Container
import androidx.ui.layout.Row
import androidx.ui.material.ColorPalette
import androidx.ui.tooling.preview.Preview

val lightThemeColors = ColorPalette(
    primary = Color.White,
    primaryVariant = Color(0xFFC20029),
    onPrimary = Color.Black,
    secondary = Color.White,
    onSecondary = Color.Black,
    background = Color(0xFFEEEEEE),
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = Color(0xFFD00036),
    onError = Color.White
)

@Preview
@Composable
fun bla() {
    val paint = +memo { Paint().apply { color = 0xFFFA6555.toInt() } }
    Container(expanded = true) {
        Row(arrangement = Arrangement.Center) {
            Draw { canvas, parentSize ->
                //        paint.color = +colorResource(R.color.red)
                canvas.nativeCanvas.drawRect(Rect(0, 0, 100, 4), paint)
            }
        }
    }
}
