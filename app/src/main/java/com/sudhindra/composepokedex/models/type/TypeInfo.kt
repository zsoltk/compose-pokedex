package com.sudhindra.composepokedex.models.type

import com.sudhindra.composepokedex.models.pokemon.SearchResult

data class TypeInfo(
    val pokemon: List<PokemonInfo>
) {
    data class PokemonInfo(
        val pokemon: SearchResult.Result
    )
}
