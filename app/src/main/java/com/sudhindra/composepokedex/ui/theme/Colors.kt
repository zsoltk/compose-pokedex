package com.sudhindra.composepokedex.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

sealed interface PokedexColors {
  val surface: Color
  val text: Color
}

object GrassColors : PokedexColors {
  override val surface: Color
    get() = Color.Green
  override val text: Color
    get() = Color.White
}

object FireColors : PokedexColors {
  override val surface: Color
    get() = Color.Red
  override val text: Color
    get() = Color.White
}

object WaterColors : PokedexColors {
  override val surface: Color
    get() = Color.Blue
  override val text: Color
    get() = Color.White
}

val LocalPokedexColors = compositionLocalOf<PokedexColors> { error("not provided") }
