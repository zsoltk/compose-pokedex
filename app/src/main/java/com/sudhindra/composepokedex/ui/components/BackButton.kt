package com.sudhindra.composepokedex.ui.components

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun BackButton() {
    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    IconButton(onClick = { backPressedDispatcher?.onBackPressed() }) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back Button"
        )
    }
}
