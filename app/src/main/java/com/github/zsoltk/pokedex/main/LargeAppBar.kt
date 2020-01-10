package com.github.zsoltk.pokedex.main

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.dp
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Container
import androidx.ui.layout.Padding
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
        color = colors.primary,
        shape = RoundedCornerShape(
            topLeft = 0.dp,
            topRight = 0.dp,
            bottomLeft = 32.dp,
            bottomRight = 32.dp
        )
    ) {
        Container(
            height = 260.dp,
            expanded = true
        ) {
            Stack {
                background()

                expanded {
                    Container(alignment = Alignment.BottomCenter) {
                        Padding(padding = 16.dp) {
                            content()
                        }
                    }
                }
            }
        }
    }
}
