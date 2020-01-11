package com.github.zsoltk.pokedex.home.appbar.elements

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.dp
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.Table
import androidx.ui.layout.TableColumnWidth
import androidx.ui.res.colorResource
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.R
import com.github.zsoltk.pokedex.home.Home
import com.github.zsoltk.pokedex.home.Home.MenuItem

@Composable
fun Menu(onMenuItemSelected: (MenuItem) -> Unit) {
    val leftCell = EdgeInsets(right = 5.dp, bottom = 10.dp)
    val rightCell = EdgeInsets(left = 5.dp, bottom = 10.dp)

    Table(
        columns = 2,
        alignment = { Alignment.TopCenter },
        columnWidth = { TableColumnWidth.Fraction(0.5f) }
    ) {
        tableRow {
            MenuItem("Pokedex", +colorResource(R.color.teal), leftCell) {
                onMenuItemSelected(MenuItem.Pokedex)
            }
            MenuItem("Moves", +colorResource(R.color.red), rightCell)
        }
        tableRow {
            MenuItem("Abilities", +colorResource(R.color.lightBlue), leftCell)
            MenuItem("Items", +colorResource(R.color.yellow), rightCell)
        }
        tableRow {
            MenuItem("Locations", +colorResource(R.color.purple), leftCell)
            MenuItem("Type charts", +colorResource(R.color.brown), rightCell)
        }
    }
}

@Preview
@Composable
fun PreviewMenu() {
    Menu {}
}
