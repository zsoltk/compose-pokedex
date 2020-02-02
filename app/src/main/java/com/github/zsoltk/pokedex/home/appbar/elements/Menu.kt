package com.github.zsoltk.pokedex.home.appbar.elements

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Container
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Padding
import androidx.ui.layout.Stack
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.colorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
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
            color = colorResource(cell.item.colorResId),
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
                    modifier = LayoutHeight(64.dp) + LayoutWidth.Fill
                ) {
                    Container(alignment = Alignment.CenterLeft) {
                        Padding(left = 16.dp) {
                            Text(
                                text = text,
                                style = MaterialTheme.typography().body1.copy(
                                    color = Color.White
                                )
                            )
                        }
                    }

                    Container(
                        alignment = Alignment.TopLeft,
                        padding = EdgeInsets(top = (-40).dp, left = (-30).dp),
                        expanded = true
                    ) {
                        Container(width = 60.dp, height = 60.dp) {
                            PokeBallSmall(
                                Color.White,
                                0.15f
                            )
                        }
                    }

                    Container(
                        alignment = Alignment.TopRight,
                        padding = EdgeInsets(right = (-20).dp),
                        expanded = true
                    ) {
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
