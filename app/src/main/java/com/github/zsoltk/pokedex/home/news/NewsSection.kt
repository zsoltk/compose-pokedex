package com.github.zsoltk.pokedex.home.news

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutGravity
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Stack
import androidx.ui.material.MaterialTheme
import androidx.ui.res.colorResource
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.R

@Preview
@Composable
fun NewsHeaderSection() {
    Stack(modifier = LayoutWidth.Fill) {
        Container(modifier = LayoutGravity.BottomLeft) {
            NewsHeader()
        }

        Container(modifier = LayoutGravity.BottomRight) {
            NewsViewAll()
        }
    }
}

@Composable
fun NewsHeader() {
    Text(
        text = "Pokémon News",
        style = MaterialTheme.typography().h6.copy(
            fontWeight = FontWeight.W900
        )
    )
}

@Composable
fun NewsViewAll() {
    Text(
        text = "View All",
        style = MaterialTheme.typography().body2.copy(
            color = colorResource(R.color.poke_blue)
        )
    )
}
