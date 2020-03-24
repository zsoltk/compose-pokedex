package com.github.zsoltk.pokedex.pokedex.section

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.Table
import androidx.ui.layout.TableColumnWidth
import androidx.ui.material.LinearProgressIndicator
import androidx.ui.material.MaterialTheme
import androidx.ui.res.colorResource
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import com.github.zsoltk.pokedex.R
import com.github.zsoltk.pokedex.entity.Pokemon

data class Stat(
    val label: String,
    val value: Int?,
    val max: Int = 100
) {
    val progress: Float =
        1f * (value ?: 0) / max
}


@Composable
fun BaseStatsSection(pokemon: Pokemon) {
    val stats = listOf(
        Stat("HP", pokemon.hp),
        Stat("Attack", pokemon.attack),
        Stat("Defense", pokemon.defense),
        Stat("Sp. Atk", pokemon.specialAttack),
        Stat("Sp. Def", pokemon.specialDefense),
        Stat("Speed", pokemon.speed),
        Stat("Total", pokemon.total, 600)
    )

    StatsTable(stats)
}

@Composable
private fun StatsTable(stats: List<Stat>) {
    Table(
        columns = 3,
        columnWidth = { columnIndex ->
            when (columnIndex) {
                0 -> TableColumnWidth.Wrap
                1 -> TableColumnWidth.Wrap
                else -> TableColumnWidth.Fraction(fraction = 0.6f)
            }
        }
    ) {
        stats.forEach {
            tableRow {
                Text(
                    text = it.label,
                    modifier = LayoutPadding(end = 16.dp, bottom = 8.dp),
                    style = MaterialTheme.typography().body2.copy(
                        color = colorResource(R.color.grey_900)
                    )
                )

                Text(
                    text = it.value.toString(),
                    modifier = LayoutPadding(end = 16.dp, bottom = 8.dp),
                    style = MaterialTheme.typography().body2.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Container(modifier = LayoutPadding(top = 8.dp)) {
                    LinearProgressIndicator(
                        progress = it.progress,
                        color = Color.Red
                    )
                }
            }
        }
    }
}
