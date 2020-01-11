package com.github.zsoltk.pokedex.main.appbar.elements

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.dp
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Stack
import androidx.ui.layout.StackChildren
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface

@Composable
fun LargeAppBar(
    background: StackChildren.() -> Unit,
    content: @Composable() () -> Unit
) {
    val colors = +MaterialTheme.colors()

    Surface(
        color = colors.surface,
        shape = RoundedCornerShape(
            bottomLeft = 32.dp,
            bottomRight = 32.dp
        )
    ) {
        Stack {
            background()

            aligned(alignment = Alignment.TopCenter) {
                content()
            }
        }
    }
}
