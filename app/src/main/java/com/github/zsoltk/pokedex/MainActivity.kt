package com.github.zsoltk.pokedex

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.*
import androidx.ui.core.*
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.res.imageResource
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = lightThemeColors) {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    val colors = +MaterialTheme.colors()
    val typography = +MaterialTheme.typography()

    FlexColumn {
        inflexible {
            LargeAppBar(background = PokeBallLarge()) {
                Column {
                    Text(
                        text = "What Pokémon\nare you looking for?",
                        style = typography.h4.copy(
                            color = colors.onPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    HeightSpacer(height = 16.dp)
                    RoundedSearchBar()
                }
            }
        }
        
        expanded(1f) {
            Container(modifier = Spacing(24.dp)) {
                Text("Body")
            }
        }
    }
}

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
                    DrawImage(image = +imageResource(R.drawable.search))
                }
                WidthSpacer(width = 16.dp)
                Text("Search Pokemon, Move, Ability, etc")
            }
        }
    }
}

@Composable
fun LargeAppBar(
    background: StackChildren.() -> Unit,
    content: @Composable() () -> Unit
) {
    val colors = +MaterialTheme.colors()

    Surface(
        color = colors.primary,
        shape = RoundedCornerShape(
            topLeft = 0.dp,
            topRight = 0.dp,
            bottomLeft = 32.dp,
            bottomRight = 32.dp
        )
    ) {
        Container(
            height = 260.dp,
            expanded = true
        ) {
            Stack {
                background()

                expanded {
                    Container(alignment = Alignment.BottomCenter) {
                        Padding(padding = 16.dp) {
                            content()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PokeBallLarge(): StackChildren.() -> Unit = {
    positioned(
        topInset =(-90).dp,
        rightInset = (-90).dp
    ) {
        Container(
            width = 260.dp,
            height = 260.dp,
            expanded = true,
            alignment = Alignment.TopRight
        ) {
            Opacity(opacity = 0.1f) {
                DrawImage(image = +imageResource(R.drawable.pokeball))
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme(colors = lightThemeColors) {
        AppContent()
    }
}
