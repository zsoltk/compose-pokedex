package com.github.zsoltk.pokedex

import androidx.annotation.CheckResult
import androidx.annotation.ColorRes
import androidx.compose.ambient
import androidx.compose.effectOf
import androidx.compose.memo
import androidx.core.content.ContextCompat
import androidx.ui.core.ContextAmbient
import androidx.ui.graphics.Color

@CheckResult(suggest = "+")
fun colorResource(@ColorRes resId: Int) =
    effectOf<Color> {
        val context =
            +ambient(ContextAmbient)
        val res = context.resources
        +memo(resId) {
            Color(
                ContextCompat.getColor(context, resId)
            )
        }
    }
