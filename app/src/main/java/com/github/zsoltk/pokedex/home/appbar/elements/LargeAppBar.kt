package com.github.zsoltk.pokedex.home.appbar.elements

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Container
import androidx.ui.layout.Stack
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.unit.dp

@Composable
fun LargeAppBar(
    background: @Composable() () -> Unit,
    content: @Composable() () -> Unit
) {
    Surface(
        color = MaterialTheme.colors().surface,
        shape = RoundedCornerShape(
            bottomLeft = 32.dp,
            bottomRight = 32.dp
        )
    ) {
        Stack {
            background()

            Container(alignment = Alignment.TopCenter) {
                content()
            }
        }
    }
}
