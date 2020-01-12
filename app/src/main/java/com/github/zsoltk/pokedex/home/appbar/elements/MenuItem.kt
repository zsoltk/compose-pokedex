package com.github.zsoltk.pokedex.home.appbar.elements

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.common.PokeBall

@Composable
fun MenuItemButton(text: String, color: Color, onClick: () -> Unit = {}) {
    Surface(
        color = color,
        shape = RoundedCornerShape(16.dp)
    ) {
        Ripple(bounded = true) {
            Clickable(onClick) {
                Stack(
                    modifier = Height(64.dp) wraps ExpandedWidth
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
                            PokeBall(
                                Color.White,
                                0.15f
                            )
                        }
                    }

                    positioned(rightInset = (-20).dp) {
                        Container(width = 96.dp, height = 96.dp) {
                            PokeBall(
                                Color.White,
                                0.15f
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMenuItem() {
    MenuItemButton(
        text = "MenuItem",
        color = Color(0xFFFA6555),
        onClick = {}
    )
}
