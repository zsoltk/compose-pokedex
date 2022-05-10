package com.sudhindra.composepokedex.models.type

import com.sudhindra.composepokedex.utils.splitAndCapitalise

data class Type(
    val id: Int,
    val name: String
) {

    val formattedName: String
        get() = name.splitAndCapitalise()
}
