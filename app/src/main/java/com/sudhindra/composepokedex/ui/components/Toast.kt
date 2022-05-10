package com.sudhindra.composepokedex.ui.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * Shows a toast Message from a Composable Function
 */
@SuppressLint("ComposableNaming")
@Composable
fun toast(msg: String) = Toast.makeText(LocalContext.current, msg, Toast.LENGTH_SHORT).show()
