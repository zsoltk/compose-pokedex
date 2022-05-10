package com.sudhindra.composepokedex.viemodel.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudhindra.composepokedex.models.pokemon.Pokemon
import com.sudhindra.composepokedex.room.FavouritePokemonRepository
import com.sudhindra.composepokedex.utils.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonRepository,
    private val favouritePokemonRepository: FavouritePokemonRepository
) : ViewModel() {

    var allPokemonPager = repository.getAllPokemon(viewModelScope)

    fun getTypedPokemon(typeId: Int) = repository.getTypedPokemon(viewModelScope, typeId)

    fun getRegionalPokemon(regionId: Int) = repository.getRegionalPokemon(viewModelScope, regionId)

    val favouritePokemonIds = favouritePokemonRepository.getFavouritePokemonIds()

    fun insertIntoFavourites(pokemon: Pokemon) = launch {
        favouritePokemonRepository.insert(pokemon)
    }

    fun deleteFromFavourites(pokemon: Pokemon) = launch {
        favouritePokemonRepository.delete(pokemon, this)
    }
}
