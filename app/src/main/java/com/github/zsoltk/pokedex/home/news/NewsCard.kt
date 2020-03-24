package com.github.zsoltk.pokedex.home.news

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Row
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.res.colorResource
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import com.github.zsoltk.pokedex.R
import com.github.zsoltk.pokedex.common.HorizontalRuler
import com.github.zsoltk.pokedex.common.LoadImage
import com.github.zsoltk.pokedex.entity.NewsItem

@Composable
fun NewsCard(newsItem: NewsItem) {
    Column {
        Row(modifier = LayoutWidth.Fill) {
            Column(modifier = LayoutWeight(1f)) {
                NewsTitle(newsItem.title)
                NewsPublishedDate(
                    newsItem.date
                )
            }

            NewsImage()
        }

        Container(modifier = LayoutPadding(top = 16.dp, bottom = 16.dp)) {
            HorizontalRuler(
                color = colorResource(
                    R.color.grey_200
                )
            )
        }
    }
}


@Composable
private fun NewsImage() {
    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = LayoutPadding(start = 48.dp)
    ) {
        Container(width = 112.dp, height = 64.dp) {
            LoadImage(R.drawable.news1)
        }
    }
}

@Composable
private fun NewsTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography().body2.copy(
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.grey_900)
        ),
        maxLines = 2,
        modifier = LayoutPadding(bottom = 4.dp)
    )
}

@Composable
private fun NewsPublishedDate(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography().overline.copy(
            color = colorResource(R.color.grey_500)
        )
    )
}
