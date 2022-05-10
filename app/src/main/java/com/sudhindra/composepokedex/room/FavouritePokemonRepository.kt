package com.sudhindra.composepokedex.room

import com.sudhindra.composepokedex.models.pokemon.Pokemon
import com.sudhindra.composepokedex.room.models.FavouritePokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class FavouritePokemonRepository @Inject constructor(
    private val dao: FavouritePokemonDao
) {

    suspend fun insert(pokemon: Pokemon) = dao.insert(FavouritePokemon(pokemon = pokemon))

    suspend fun update(pokemon: Pokemon) = dao.update(FavouritePokemon(pokemon = pokemon))

    suspend fun delete(favouritePokemon: FavouritePokemon) = dao.delete(favouritePokemon)

    suspend fun delete(id: Int, pokemon: Pokemon) =
        dao.delete(FavouritePokemon(id, pokemon))

    suspend fun delete(pokemon: Pokemon, scope: CoroutineScope) {
        val id = getPrimaryKeyForPokemonId(pokemon.pokemonId, scope)
        if (id != null) delete(id, pokemon)
    }

    fun getFavouritePokemon() = dao.getFavouritePokemon()

    fun getFavouritePokemonIds() =
        dao.getFavouritePokemon().map { it.map { favourite -> favourite.pokemon.pokemonId } }

    private suspend fun getPrimaryKeyForPokemonId(pokemonId: Int, scope: CoroutineScope) = dao
        .getFavouritePokemon()
        .map { it.find { favourite -> favourite.pokemon.pokemonId == pokemonId }?.id }
        .stateIn(scope)
        .value

    fun pokemonIsInFavourites(pokemonId: Int) = dao.getFavouritePokemon()
        .map { it.find { favourite -> favourite.pokemon.pokemonId == pokemonId } != null }
}
