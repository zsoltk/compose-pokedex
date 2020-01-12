package com.github.zsoltk.pokedex.common

import androidx.compose.Composable
import androidx.ui.core.Dp
import androidx.ui.core.dp
import androidx.ui.layout.Padding
import androidx.ui.layout.Table
import androidx.ui.layout.TableColumnWidth

@Composable
fun <T> TableRenderer(cols: Int, items: List<T>, cellSpacing: Dp, cellRenderer: @Composable() (Cell<T>) -> Unit) {
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
                    val cell = Cell(
                        item = items[j],
                        index = j,
                        rowIndex = i,
                        colIndex = j - startIndex
                    )

                    Padding(
                        left = if (cell.colIndex > 0) cellSpacing else 0.dp,
                        top = if (cell.rowIndex > 0) cellSpacing else 0.dp,
                        right = if (cell.colIndex < cols - 1) cellSpacing else 0.dp,
                        bottom = if (cell.rowIndex < rows - 1) cellSpacing else 0.dp
                    ) {
                        cellRenderer(
                            cell
                        )
                    }

                }
            }
        }
    }
}

data class Cell<T>(
    /**
     * The associated item to be rendered.
     */
    val item: T,

    /**
     * The index of the item in the original list passed to [TableRenderer]
     */
    val index: Int,
    /**
     * The row index in which this table cell is rendered.
     */
    val rowIndex: Int,

    /**
     * The column index in which this table cell is rendered.
     */
    val colIndex: Int
) {
    fun isFirstRow(): Boolean =
        rowIndex == 0

    fun isFirstColumn(): Boolean =
        colIndex == 0
}
