package com.sudhindra.composepokedex.ui.components.pokemonDetails.subinfo

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudhindra.composepokedex.models.pokemon.Pokemon

val SectionBody1 = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold)
val SectionBody2 = TextStyle(fontSize = 15.sp)

@Composable
fun PokemonStatsSection(pokemon: Pokemon) {
    Card(shape = RoundedCornerShape(15.dp)) {
        Column(
            Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            pokemon.stats.forEach {
                Stat(name = it.stat.formattedName, value = it.base_stat)
            }
        }
    }
}

@Composable
private fun Stat(
    name: String,
    value: Int
) {
    var animationDone by remember { mutableStateOf(false) }
    val statValue by animateIntAsState(
        targetValue = if (animationDone) value else 0,
        animationSpec = tween(800)
    )
    val progress by animateFloatAsState(
        targetValue = if (animationDone) value.progressForStat() else 0f,
        animationSpec = tween(800)
    )
    SideEffect {
        animationDone = true
    }
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.width(60.dp),
            text = name.replace("Special Attack", "Sp. Atk")
                .replace("Special Defense", "Sp. Def"),
            style = SectionBody2
        )
        Text(
            modifier = Modifier.width(30.dp),
            text = statValue.toString(),
            style = SectionBody1,
            textAlign = TextAlign.Center
        )
        LinearProgressIndicator(progress)
    }
}

private fun Int.progressForStat() = this / when {
    this <= 100 -> 100f
    this <= 150 -> 150f
    this <= 200 -> 200f
    this <= 250 -> 250f
    else -> 300f
}
