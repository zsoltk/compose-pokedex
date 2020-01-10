package com.github.zsoltk.pokedex.main.menu

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.dp
import androidx.ui.layout.Padding
import androidx.ui.layout.Table
import androidx.ui.layout.TableColumnWidth
import androidx.ui.res.colorResource
import com.github.zsoltk.pokedex.R

@Composable
fun Menu() {
    Padding(padding = 11.dp) {
        Table(
            columns = 2,
            alignment = { Alignment.TopCenter },
            columnWidth = { TableColumnWidth.Fraction(0.5f) }
        ) {
            tableRow {
                MenuItem("Pokedex", +colorResource(R.color.teal))
                MenuItem("Moves", +colorResource(R.color.red))
            }
            tableRow {
                MenuItem("Abilities", +colorResource(R.color.lightBlue))
                MenuItem("Items", +colorResource(R.color.yellow))
            }
            tableRow {
                MenuItem("Locations", +colorResource(R.color.purple))
                MenuItem("Type charts", +colorResource(R.color.brown))
            }
        }
    }
}
