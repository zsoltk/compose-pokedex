package com.sudhindra.composepokedex.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.sudhindra.composepokedex.ui.screens.MainUi
import com.sudhindra.composepokedex.ui.utils.WithTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WithTheme {
                MainUi()
            }
        }
    }
}
