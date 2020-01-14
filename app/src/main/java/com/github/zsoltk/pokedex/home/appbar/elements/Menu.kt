package com.github.zsoltk.pokedex.home.appbar.elements

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Container
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.Height
import androidx.ui.layout.Padding
import androidx.ui.layout.Stack
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.colorResource
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.common.PokeBallSmall
import com.github.zsoltk.pokedex.common.TableRenderer
import com.github.zsoltk.pokedex.home.Home.MenuItem
import com.github.zsoltk.pokedex.home.Home.MenuItem.Abilities
import com.github.zsoltk.pokedex.home.Home.MenuItem.Items
import com.github.zsoltk.pokedex.home.Home.MenuItem.Locations
import com.github.zsoltk.pokedex.home.Home.MenuItem.Moves
import com.github.zsoltk.pokedex.home.Home.MenuItem.Pokedex
import com.github.zsoltk.pokedex.home.Home.MenuItem.TypeCharts

@Composable
fun Menu(onMenuItemSelected: (MenuItem) -> Unit) {
    val menuItems = listOf(
        Pokedex, Moves,
        Abilities, Items,
        Locations, TypeCharts
    )

    TableRenderer(cols = 2, cellSpacing = 5.dp, items = menuItems) { cell ->
        MenuItemButton(
            text = cell.item.label,
            color = +colorResource(cell.item.colorResId),
            onClick = { onMenuItemSelected(cell.item) }
        )
    }
}

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
                            PokeBallSmall(
                                Color.White,
                                0.15f
                            )
                        }
                    }

                    positioned(rightInset = (-20).dp) {
                        Container(width = 96.dp, height = 96.dp) {
                            PokeBallSmall(
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
fun PreviewMenu() {
    Menu {}
}
