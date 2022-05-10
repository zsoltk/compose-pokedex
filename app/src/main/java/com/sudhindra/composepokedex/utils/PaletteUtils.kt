package com.sudhindra.composepokedex.utils

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.palette.graphics.Palette

/**
 * Better to Call from a Coroutine
 */
fun Drawable.generatePalette(onPaletteReady: (Palette) -> Unit) {
    val bitmap = (this as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
    Palette.from(bitmap).generate { palette -> palette?.let(onPaletteReady) }
}
