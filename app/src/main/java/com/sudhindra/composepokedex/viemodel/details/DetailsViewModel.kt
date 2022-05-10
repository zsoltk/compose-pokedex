package com.sudhindra.composepokedex.viemodel.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sudhindra.composepokedex.api.PokeApi
import com.sudhindra.composepokedex.models.pokemon.EvolutionChain
import com.sudhindra.composepokedex.models.pokemon.Pokemon
import com.sudhindra.composepokedex.models.pokemon.PokemonSpecies
import com.sudhindra.composepokedex.room.FavouritePokemonRepository
import com.sudhindra.composepokedex.utils.launch
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailsViewModel @AssistedInject constructor(
    private val api: PokeApi,
    private val favouritePokemonRepository: FavouritePokemonRepository,
    @Assisted val pokemon: Pokemon
) : ViewModel() {

    val inFavourites = favouritePokemonRepository.pokemonIsInFavourites(pokemon.pokemonId)

    fun insertIntoFavourites() = launch {
        favouritePokemonRepository.insert(pokemon)
    }

    fun deleteFromFavourites() = launch {
        favouritePokemonRepository.delete(pokemon, this)
    }

    private val _evolutionChainState: MutableStateFlow<EvolutionChainState> =
        MutableStateFlow(EvolutionChainState.Loading)
    val evolutionChainState: StateFlow<EvolutionChainState> = _evolutionChainState

    fun getEvolutionChain(id: Int) = launch {
        try {
            val species = api.getSpecies(id).body()!!
            val evolutionChain = api.getEvolutionChain(species.evolution_chain.url).body()!!
            _evolutionChainState.value = EvolutionChainState.Success(evolutionChain)
        } catch (e: Exception) {
            e.printStackTrace()
            _evolutionChainState.value = EvolutionChainState.Error("Failed to get Evolution Chain")
        }
    }

    private val _newPokemonState: MutableStateFlow<NewPokemonState> =
        MutableStateFlow(NewPokemonState.Nothing)
    val newPokemonState: StateFlow<NewPokemonState> = _newPokemonState

    fun getNewPokemonInfo(species: PokemonSpecies) = launch {
        try {
            _newPokemonState.value = NewPokemonState.Loading
            val info = api.getPokemonDetails(species.name).body()!!
            _newPokemonState.value = NewPokemonState.Success(info)
        } catch (e: Exception) {
            e.printStackTrace()
            _newPokemonState.value =
                NewPokemonState.Error("Failed to get Info for ${species.formattedName}")
        }
    }

    // Assisted Injection Logic
    @AssistedFactory
    interface AssistedInjectFactory {
        fun create(pokemon: Pokemon): DetailsViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedInjectFactory,
            pokemon: Pokemon
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>) =
                assistedFactory.create(pokemon) as T
        }
    }
}

sealed class EvolutionChainState {
    object Loading : EvolutionChainState()
    data class Success(val evolutionChain: EvolutionChain) : EvolutionChainState()
    data class Error(val msg: String) : EvolutionChainState()
}

sealed class NewPokemonState {
    object Nothing : NewPokemonState()
    object Loading : NewPokemonState()
    data class Success(val pokemon: Pokemon) : NewPokemonState()
    data class Error(val msg: String) : NewPokemonState()
}
