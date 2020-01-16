package com.github.zsoltk.pokedex.common

import androidx.annotation.CheckResult
import androidx.compose.effectOf
import androidx.compose.memo
import androidx.compose.onCommit
import androidx.compose.state
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


sealed class AsyncState<T> {
    class Initialised<T>: AsyncState<T>()
    class Loading<T>: AsyncState<T>()
    data class Error<T>(val error: Throwable): AsyncState<T>()
    data class Result<T>(val result: T): AsyncState<T>()
}

/**
 * Based on https://medium.com/swlh/android-mvi-with-jetpack-compose-b0890f5156ac
 */
@CheckResult(suggest = "+")
fun <T> observe(data: LiveData<T>) = effectOf<T?> {
    var result by +state { data.value }
    val observer = +memo { Observer<T> { result = it } }

    +onCommit(data) {
        data.observeForever(observer)
        onDispose { data.removeObserver(observer) }
    }

    result
}
