package com.sudhindra.composepokedex.models.pokemon

data class EvolutionChain(
    val id: Int,
    val chain: ChainLink?
) {
    data class ChainLink(
        val species: PokemonSpecies,
        val evolves_to: List<ChainLink?>
    )
}
