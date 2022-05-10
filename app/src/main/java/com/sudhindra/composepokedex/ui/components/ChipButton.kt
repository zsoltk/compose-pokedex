package com.sudhindra.composepokedex.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChipButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.colors.primary,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = CircleShape,
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        content = content,
        colors = ButtonDefaults.buttonColors(backgroundColor)
    )
}
