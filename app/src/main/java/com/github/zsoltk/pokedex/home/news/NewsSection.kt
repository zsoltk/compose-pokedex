package com.github.zsoltk.pokedex.home.news

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutGravity
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Spacer
import androidx.ui.layout.Stack
import androidx.ui.material.MaterialTheme
import androidx.ui.res.colorResource
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.github.zsoltk.pokedex.R
import com.github.zsoltk.pokedex.entity.NewsItem

@Preview
@Composable
fun NewsSection() {
    Container(expanded = true) {
        Column {
            NewsHeaderSection()
            Spacer(modifier = LayoutHeight(32.dp))
            NewsList()
        }
    }
}

@Composable
fun NewsHeaderSection() {
    Stack(modifier = LayoutWidth.Fill) {
        Container(modifier = LayoutGravity.BottomStart) {
            NewsHeader()
        }

        Container(modifier = LayoutGravity.BottomEnd) {
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

@Composable
fun NewsList() {
    val news = listOf(
        NewsItem(),
        NewsItem(),
        NewsItem(),
        NewsItem(),
        NewsItem(),
        NewsItem(),
        NewsItem()
    )

    news.forEach {
        NewsCard(it)
    }
}
