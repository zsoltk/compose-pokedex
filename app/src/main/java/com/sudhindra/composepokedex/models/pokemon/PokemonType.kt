package com.sudhindra.composepokedex.models.pokemon

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonType(
    val type: Type,
    val slot: Int
): Parcelable {
    @Parcelize
    data class Type(
        val id: Int,
        val name: String
    ): Parcelable
}
