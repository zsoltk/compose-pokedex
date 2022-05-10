package com.sudhindra.composepokedex.models.pokemon

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ability(
    val name: String,
    val url: String
) : Parcelable

@Parcelize
data class AbilityObject(
    val ability: Ability
) : Parcelable
