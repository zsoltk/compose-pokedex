package com.github.zsoltk.pokedex.home.appbar.elements

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.foundation.Image
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutGravity
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Row
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
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
            modifier = LayoutPadding(start = 16.dp, end = 16.dp)
        ) {
            Row(modifier = LayoutWidth.Fill) {
                Container(
                    modifier = LayoutGravity.Center,
                    alignment = Alignment.CenterStart,
                    width = 24.dp, height = 24.dp
                ) {
                    Image(
                        image = imageResource(
                            R.drawable.search
                        )
                    )
                }
                Container(
                    modifier = LayoutWeight(1f) + LayoutGravity.Center +
                        LayoutPadding(start = 16.dp, end = 16.dp),
                    alignment = Alignment.CenterStart
                ) {
                    Text("Search Pokemon, Move, Ability, etc")
                }
            }
        }
    }
}
