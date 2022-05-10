package com.sudhindra.composepokedex.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Placeholder(modifier: Modifier) {
    Center(modifier) {
        CircularProgressIndicator()
    }
}

@Composable
fun RetryButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Center(modifier) {
        IconButton(
            modifier = Modifier.border(1.dp, MaterialTheme.colors.onBackground, CircleShape),
            onClick = onClick
        ) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Retry Button")
        }
    }
}
