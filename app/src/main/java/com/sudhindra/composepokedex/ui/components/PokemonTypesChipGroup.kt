package com.sudhindra.composepokedex.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudhindra.composepokedex.models.pokemon.PokemonType
import com.sudhindra.composepokedex.utils.capitalize

@Composable
fun PokemonTypesChipGroup(types: List<PokemonType>) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        types.forEach {
            ChipButton(
                onClick = { },
            ) {
                Text(
                    text = it.type.name.capitalize(),
                    style = TextStyle(fontSize = 13.sp, color = Color.White)
                )
            }
        }
    }
}
