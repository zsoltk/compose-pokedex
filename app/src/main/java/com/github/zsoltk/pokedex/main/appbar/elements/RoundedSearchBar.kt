package com.github.zsoltk.pokedex.main.appbar.elements

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Container
import androidx.ui.layout.Row
import androidx.ui.layout.Spacing
import androidx.ui.layout.WidthSpacer
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.R

@Preview
@Composable
fun RoundedSearchBar() {
    val colors = +MaterialTheme.colors()

    Surface(
        color = colors.background,
        shape = RoundedCornerShape(24.dp)
    ) {
        Container(
            height = 48.dp,
            expanded = true,
            alignment = Alignment.CenterLeft,
            modifier = Spacing(left = 16.dp)
        ) {
            Row {
                Container(width = 24.dp, height = 24.dp) {
                    DrawImage(
                        image = +imageResource(
                            R.drawable.search
                        )
                    )
                }
                WidthSpacer(width = 16.dp)
                Text("Search Pokemon, Move, Ability, etc")
            }
        }
    }
}
