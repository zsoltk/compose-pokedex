package com.sudhindra.composepokedex.ui.utils

import androidx.compose.material.*
import androidx.compose.runtime.*

@Composable
fun WithTheme(content: @Composable () -> Unit) {
    ProvideChangeTheme {
        MaterialTheme(
            colors = LocalColors.current
        ) {
            content()
        }
    }
}

@Composable
fun ProvideColors(
    isLightTheme: Boolean,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColors provides if (isLightTheme) lightColors() else darkColors(),
        LocalContentAlpha provides 1f,
        content = content
    )
}

@Composable
fun ProvideChangeTheme(
    content: @Composable () -> Unit,
) {
    var isLightTheme by remember { mutableStateOf(true) }

    CompositionLocalProvider(
        LocalTheme provides object : ChangeTheme {
            override fun toggleTheme() {
                isLightTheme = !isLightTheme
            }
        },
        content = {
            ProvideColors(
                isLightTheme = isLightTheme
            ) {
                content()
            }
        }
    )

}

internal val LocalColors = staticCompositionLocalOf<Colors> {
    error("No LocalColors provided")
}

internal val LocalTheme = staticCompositionLocalOf<ChangeTheme> {
    error("No ChangeTheme provided")
}

interface ChangeTheme {
    fun toggleTheme()
}
