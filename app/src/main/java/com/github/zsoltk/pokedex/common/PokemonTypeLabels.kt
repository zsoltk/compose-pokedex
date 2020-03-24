package com.github.zsoltk.pokedex.common

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Spacer
import androidx.ui.material.Surface
import androidx.ui.res.colorResource
import androidx.ui.text.TextStyle
import androidx.ui.unit.Dp
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.github.zsoltk.pokedex.R
import com.github.zsoltk.pokedex.appFontFamily

data class TypeLabelMetrics(
    val cornerRadius: Dp,
    val fontSize: TextUnit,
    val verticalPadding: Dp,
    val horizontalPadding: Dp,
    val elementSpacing: Dp
) {
    companion object {
        val SMALL = TypeLabelMetrics(24.dp, 9.sp, 3.dp, 8.dp, 8.dp)
        val MEDIUM = TypeLabelMetrics(24.dp, 12.sp, 4.dp, 12.dp, 8.dp)
    }
}

@Composable
fun PokemonTypeLabels(types: List<String>?, metrics: TypeLabelMetrics) {
    types?.forEach {
        Surface(
            color = Color(0x38FFFFFF),
            shape = RoundedCornerShape(metrics.cornerRadius)
        ) {
            PokemonTypeLabel(it, metrics)
        }
        Spacer(modifier = LayoutWidth(metrics.elementSpacing) + LayoutHeight(metrics.elementSpacing))
    }
}

@Composable
fun PokemonTypeLabel(text: String, metrics: TypeLabelMetrics) {
    Text(
        text = text,
        modifier = LayoutPadding(
            start = metrics.horizontalPadding,
            end = metrics.horizontalPadding,
            top = metrics.verticalPadding,
            bottom = metrics.verticalPadding
        ),
        style = TextStyle(
            fontFamily = appFontFamily,
            fontSize = metrics.fontSize,
            color = colorResource(R.color.white_1000)
        )
    )
}
