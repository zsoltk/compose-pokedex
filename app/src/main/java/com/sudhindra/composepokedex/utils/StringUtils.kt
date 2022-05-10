package com.sudhindra.composepokedex.utils

import java.util.*

fun String.capitalize() = capitalize(Locale.ROOT)

fun String.splitAndCapitalise() = split("-")
    .joinToString(separator = " ") {
        it.capitalize()
    }
