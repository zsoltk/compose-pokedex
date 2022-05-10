package com.sudhindra.composepokedex.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T> Context.createIntent() = Intent(this, T::class.java)

inline fun <reified T> Activity.gotoActivity() {
    val intent = this.createIntent<T>()
    startActivity(intent)
}
