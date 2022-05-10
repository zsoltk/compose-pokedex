package com.sudhindra.composepokedex.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

enum class ThemeType {
  GRASS, FIRE, WATER
}

object PokedexTheme {

  val colors
    @Composable
    @ReadOnlyComposable
    get() = LocalPokedexColors.current

}

@Composable
fun PokedexTheme(type: ThemeType, content: @Composable () -> Unit) {
  val colors = when (type) {
    ThemeType.GRASS -> GrassColors
    ThemeType.FIRE -> FireColors
    ThemeType.WATER -> WaterColors
  }

  CompositionLocalProvider(
    LocalPokedexColors provides colors,
    content = content
  )
}
