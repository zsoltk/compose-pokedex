package com.github.zsoltk.pokedex.common

import androidx.compose.Composable
import androidx.ui.layout.Table
import androidx.ui.layout.TableColumnWidth

@Composable
fun <T> TableRenderer(cols: Int, items: List<T>, childRenderer: @Composable() (T) -> Unit) {
    val rows = items.size / cols
    val lastIndex = items.lastIndex

    Table(
        columns = cols,
        columnWidth = { TableColumnWidth.Fraction(1.0f / cols) }) {
        for (i in 0..rows) {
            tableRow {
                val startIndex = i * cols
                val maxIndex = (i + 1) * cols - 1
                val endIndex = if (maxIndex > lastIndex) lastIndex else maxIndex

                for (j in startIndex..endIndex) {
                    childRenderer(items[j])
                }
            }
        }
    }
}
