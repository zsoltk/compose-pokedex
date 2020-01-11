package com.github.zsoltk.pokedex.pokedex

import com.github.zsoltk.pokedex.R

data class Pokemon(
    val id: String? = null,
    val name: String? = null,
    val typeofpokemon: List<String>? = null,
    val image: Int? = null
)


fun Pokemon.color(): Int {
    val type = typeofpokemon?.elementAtOrNull(0)

    return when (type?.toLowerCase()) {
        "grass", "bug" -> R.color.lightTeal
        "fire" -> R.color.lightRed
        "water", "fighting", "normal" -> R.color.lightBlue
        "electric", "psychic" -> R.color.lightYellow
        "poison", "ghost" -> R.color.lightPurple
        "ground", "rock" -> R.color.lightBrown
        "dark" -> R.color.black
        else -> return R.color.lightBlue
    }
}

val pokemons = listOf(
    Pokemon(
        id = "#001",
        name = "Bulbasaur",
        typeofpokemon = listOf("Grass", "Poison"),
        image = R.drawable.poke001
    ),
    Pokemon(
        id = "#002",
        name = "Ivysaur",
        typeofpokemon = listOf("Grass", "Poison"),
        image = R.drawable.poke002
    ),
    Pokemon(
        id = "#003",
        name = "Venusaur",
        typeofpokemon = listOf("Grass", "Poison"),
        image = R.drawable.poke003
    ),
    Pokemon(
        id = "#004",
        name = "Charmander",
        typeofpokemon = listOf("Fire"),
        image = R.drawable.poke004
    ),
    Pokemon(
        id = "#005",
        name = "Charmeleon",
        typeofpokemon = listOf("Fire"),
        image = R.drawable.poke005
    ),
    Pokemon(
        id = "#006",
        name = "Charizard",
        typeofpokemon = listOf("Fire"),
        image = R.drawable.poke006
    ),
    Pokemon(
        id = "#007",
        name = "Squirtle",
        typeofpokemon = listOf("Water"),
        image = R.drawable.poke007
    ),
    Pokemon(
        id = "#008",
        name = "Wartortle",
        typeofpokemon = listOf("Water"),
        image = R.drawable.poke008
    ),
    Pokemon(
        id = "#009",
        name = "Blastoise",
        typeofpokemon = listOf("Water"),
        image = R.drawable.poke009
    )
)
