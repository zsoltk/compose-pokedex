package com.github.zsoltk.pokedex.pokedex

import androidx.compose.Composable
import com.github.zsoltk.compose.router.Router
import com.github.zsoltk.pokedex.entity.Pokemon

interface Pokedex {

    sealed class Routing {
        object PokemonList : Routing()
        data class PokemonDetails(val pokemon: Pokemon) : Routing()
    }

    companion object {
        @Composable
        fun Content(defaultRouting: Routing = Routing.PokemonList) {
            Router(defaultRouting) { backStack ->
                when (val routing = backStack.last()) {
                    is Routing.PokemonList -> PokemonList.Content(
                        onPokemonSelected = { backStack.push(Routing.PokemonDetails(it)) }
                    )

                    is Routing.PokemonDetails -> PokemonDetails.Content(routing.pokemon)
                }
            }
        }
    }
}
