package com.sudhindra.composepokedex.models.pokemon

import com.sudhindra.composepokedex.utils.splitAndCapitalise

data class PokemonSpecies(
    val name: String,
    val url: String,
    val evolution_chain: EvolutionChainObj
) {
    data class EvolutionChainObj(val url: String)

    val formattedName: String
        get() = name.splitAndCapitalise()

    val pokemonId: Int
        get() = url.substringAfter("pokemon-species/")
            .replace("/", "")
            .toInt()

    val pokemonSprite: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"
}
