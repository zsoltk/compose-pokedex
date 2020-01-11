package com.github.zsoltk.pokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.FlexColumn
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.pokedex.main.appbar.MainAppBar

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
            Text("Body")
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
