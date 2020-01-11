package com.github.zsoltk.pokedex.home.news

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.FlexRow
import androidx.ui.layout.Padding
import androidx.ui.layout.Spacing
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.res.colorResource
import androidx.ui.res.imageResource
import androidx.ui.text.font.FontWeight
import com.github.zsoltk.pokedex.R

@Composable
fun NewsCard(newsItem: NewsItem) {
    Column {
        FlexRow(modifier = ExpandedWidth) {
            expanded(1f) {
                Column {
                    NewsTitle(newsItem.title)
                    NewsPublishedDate(
                        newsItem.date
                    )
                }
            }

            inflexible {
                NewsImage()
            }
        }

        Padding(top = 16.dp, bottom = 16.dp) {
            HorizontalRuler(color = +colorResource(R.color.grey_200))
        }
    }
}


@Composable
private fun NewsImage() {
    Padding(left = 48.dp) {
        Surface(shape = RoundedCornerShape(8.dp)) {
            Container(width = 112.dp, height = 64.dp) {
                DrawImage(image = +imageResource(R.drawable.news1))
            }
        }
    }
}

@Composable
private fun NewsTitle(text: String) {
    val typography = +MaterialTheme.typography()
    val colors = +MaterialTheme.colors()

    Text(
        text = text,
        style = typography.body2.copy(
            fontWeight = FontWeight.Bold,
            color = +colorResource(R.color.grey_900)
        ),
        maxLines = 2,
        modifier = Spacing(bottom = 4.dp)
    )
}

@Composable
private fun NewsPublishedDate(text: String) {
    val typography = +MaterialTheme.typography()

    Text(
        text = text,
        style = typography.overline.copy(
            color = +colorResource(R.color.grey_500)
        )
    )
}
