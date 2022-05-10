package com.sudhindra.composepokedex.viemodel

import androidx.lifecycle.ViewModel
import com.sudhindra.composepokedex.room.FavouritePokemonRepository
import com.sudhindra.composepokedex.room.models.FavouritePokemon
import com.sudhindra.composepokedex.utils.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val favouritePokemonRepository: FavouritePokemonRepository
) : ViewModel() {

    val favouritePokemon: Flow<List<FavouritePokemon>>
        get() = favouritePokemonRepository.getFavouritePokemon()

    fun deleteFromFavourites(favouritePokemon: FavouritePokemon) = launch {
        favouritePokemonRepository.delete(favouritePokemon)
    }
}
