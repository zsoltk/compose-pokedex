package com.github.zsoltk.pokedex.common

import androidx.compose.Composable
import androidx.ui.core.drawOpacity
import androidx.ui.foundation.Image
import androidx.ui.graphics.BlendMode
import androidx.ui.graphics.Color
import androidx.ui.graphics.ColorFilter
import androidx.ui.res.loadImageResource

@Composable
fun LoadImage(
    imageResId: Int,
    tint: Color? = null,
    opacity: Float = 1.0f
) {
    loadImageResource(imageResId).resource.resource?.let {
        Image(
            modifier = drawOpacity(opacity),
            image = it,
            colorFilter = tint?.let { color ->
                ColorFilter(
                    color = color,
                    blendMode = BlendMode.srcIn
                )
            }
        )
    }
}
