package com.sudhindra.composepokedex.models.region

import com.sudhindra.composepokedex.models.pokemon.SearchResult

data class RegionInfo(
    val pokedexes: List<SearchResult.Result>
)
