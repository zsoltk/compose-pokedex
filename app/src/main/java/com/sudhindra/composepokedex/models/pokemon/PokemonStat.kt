package com.sudhindra.composepokedex.models.pokemon

import android.os.Parcelable
import com.sudhindra.composepokedex.utils.splitAndCapitalise
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonStat(
    val stat: Stat,
    val base_stat: Int
) : Parcelable {
    @Parcelize
    data class Stat(
        val id: Int,
        val name: String
    ) : Parcelable {
        val formattedName: String
            get() = name.splitAndCapitalise()
    }
}
