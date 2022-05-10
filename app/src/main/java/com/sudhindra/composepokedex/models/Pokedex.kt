package com.sudhindra.composepokedex.models

import com.google.gson.annotations.SerializedName
import com.sudhindra.composepokedex.models.pokemon.SearchResult

data class Pokedex(
    @SerializedName("pokemon_entries")
    val pokemonEntries: List<PokemonSpeciesObj>
) {
    data class PokemonSpeciesObj(
        @SerializedName("pokemon_species")
        val pokemonSpecies: SearchResult.Result
    )
}
