package com.sudhindra.composepokedex.models.location

data class Location(
    val areas: List<Area>,
    val id: Int,
    val name: String,
    val region: Region
)
