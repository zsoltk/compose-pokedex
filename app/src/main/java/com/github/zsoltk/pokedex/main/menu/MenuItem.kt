package com.github.zsoltk.pokedex.main.menu

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface

@Composable
fun MenuItem(text: String, color: Color) {
    Padding(padding = 5.dp) {
        Surface(
            color = color,
            shape = RoundedCornerShape(16.dp)
        ) {
            Stack(
                modifier = Height(
                    64.dp
                ) wraps ExpandedWidth
            ) {
                aligned(Alignment.CenterLeft) {
                    Padding(left = 16.dp) {
                        Text(
                            text = text,
                            style = (+MaterialTheme.typography()).body1.copy(
                                color = Color.White
                            )
                        )
                    }
                }

                positioned(topInset = (-40).dp, leftInset = (-30).dp) {
                    Container(width = 60.dp, height = 60.dp) {
                        WhiteTranslucentPokeBall()
                    }
                }

                positioned(rightInset = (-20).dp) {
                    Container(width = 96.dp, height = 96.dp) {
                        WhiteTranslucentPokeBall()
                    }
                }
            }
        }
    }
}
