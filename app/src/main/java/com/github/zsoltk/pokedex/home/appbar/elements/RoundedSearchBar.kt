package com.github.zsoltk.pokedex.home.appbar.elements

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Row
import androidx.ui.layout.Spacer
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.github.zsoltk.pokedex.R

@Preview
@Composable
fun RoundedSearchBar() {
    Surface(
        color = MaterialTheme.colors().background,
        shape = RoundedCornerShape(24.dp)
    ) {
        Container(
            height = 48.dp,
            expanded = true,
            alignment = Alignment.CenterLeft,
            modifier = LayoutPadding(left = 16.dp)
        ) {
            Row {
                Container(width = 24.dp, height = 24.dp) {
                    DrawImage(
                        image = imageResource(
                            R.drawable.search
                        )
                    )
                }
                Spacer(modifier = LayoutWidth(16.dp))
                Text("Search Pokemon, Move, Ability, etc")
            }
        }
    }
}
