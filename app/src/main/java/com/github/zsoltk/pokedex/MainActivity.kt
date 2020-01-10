package com.github.zsoltk.pokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.*
import androidx.ui.core.*
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.main.appbar.MainAppBar
import com.github.zsoltk.pokedex.main.menu.Menu

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
    FlexColumn {
        inflexible {
            MainAppBar()
        }

        expanded(1f) {
            Container(alignment = Alignment.TopCenter) {
                Menu()
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
