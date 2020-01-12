package com.github.zsoltk.pokedex.home.appbar.elements

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.dp
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.Padding
import androidx.ui.layout.Table
import androidx.ui.layout.TableColumnWidth
import androidx.ui.res.colorResource
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.R
import com.github.zsoltk.pokedex.common.TableRenderer
import com.github.zsoltk.pokedex.home.Home.MenuItem
import com.github.zsoltk.pokedex.home.Home.MenuItem.*

@Composable
fun Menu(onMenuItemSelected: (MenuItem) -> Unit) {
    val menuItems = listOf(
        Pokedex, Moves,
        Abilities, Items,
        Locations, TypeCharts
    )

    TableRenderer(cols = 2, items = menuItems) { colIndex , menuItem ->
        Padding(
            right = if (colIndex == 0) 5.dp else 0.dp,
            left = if (colIndex == 1) 5.dp else 0.dp,
            bottom = 10.dp
        ) {
            MenuItemButton(
                text = menuItem.label,
                color = +colorResource(menuItem.colorResId),
                onClick = { onMenuItemSelected(menuItem) }
            )
        }
    }
}

@Preview
@Composable
fun PreviewMenu() {
    Menu {}
}
