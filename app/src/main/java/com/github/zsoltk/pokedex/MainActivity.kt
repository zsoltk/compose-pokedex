package com.github.zsoltk.pokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.compose.backpress.BackPressHandler

class MainActivity : AppCompatActivity() {
    private val backPressHandler = BackPressHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = lightThemeColors) {
                backPressHandler.Provider {
                    Root.Content()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!backPressHandler.handle()) {
            super.onBackPressed()
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme(colors = lightThemeColors) {
        Root.Content()
    }
}
