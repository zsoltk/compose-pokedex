package com.sudhindra.composepokedex.viemodel.pokemon

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.sudhindra.composepokedex.api.PokeApi
import com.sudhindra.composepokedex.constants.PaginationConstants
import com.sudhindra.composepokedex.paging.pokemon.AllPokemonPagingSource
import com.sudhindra.composepokedex.paging.pokemon.RegionalPokemonPagingSource
import com.sudhindra.composepokedex.paging.pokemon.TypedPokemonPagingSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {
    fun getAllPokemon(scope: CoroutineScope) = Pager(
        config = PagingConfig(pageSize = PaginationConstants.PAGE_SIZE),
        pagingSourceFactory = { AllPokemonPagingSource(api, scope) }
    )

    fun getTypedPokemon(scope: CoroutineScope, typeId: Int) = Pager(
        config = PagingConfig(pageSize = PaginationConstants.PAGE_SIZE),
        pagingSourceFactory = { TypedPokemonPagingSource(api, scope, typeId) }
    )

    fun getRegionalPokemon(scope: CoroutineScope, regionId: Int) = Pager(
        config = PagingConfig(pageSize = PaginationConstants.PAGE_SIZE),
        pagingSourceFactory = { RegionalPokemonPagingSource(api, scope, regionId) }
    )
}
