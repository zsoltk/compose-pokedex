package com.sudhindra.composepokedex.paging.pokemon

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sudhindra.composepokedex.api.PokeApi
import com.sudhindra.composepokedex.constants.PaginationConstants
import com.sudhindra.composepokedex.models.pokemon.Pokemon
import com.sudhindra.composepokedex.utils.splitAndCapitalise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import retrofit2.HttpException
import java.io.IOException

class TypedPokemonPagingSource(
    private val api: PokeApi,
    private val scope: CoroutineScope,
    private val typeId: Int
) : PagingSource<Int, Pokemon>() {

    private var pokemonIds = listOf<Int>()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page = params.key ?: 1
        val offset = (page - 1) * PaginationConstants.PAGE_SIZE

        return try {
            if (pokemonIds.isEmpty())
                pokemonIds = api.getTypeInfo(typeId).body()!!.pokemon.map { it.pokemon.pokemonIdFromUrl }

            if (offset > pokemonIds.lastIndex)
                return LoadResult.Page(
                    listOf(),
                    if (page == 1) null else page - 1,
                    null
                )

            val toIndex =
                if (offset + PaginationConstants.PAGE_SIZE <= pokemonIds.lastIndex) offset + PaginationConstants.PAGE_SIZE else pokemonIds.size
            val pokemon = pokemonIds.subList(offset, toIndex)
                .map { scope.async { api.getPokemonDetails(it).body()!! } }
                .awaitAll()
                .map { it.copy(name = it.name.splitAndCapitalise()) }

            LoadResult.Page(
                pokemon,
                if (page == 1) null else page - 1,
                if (pokemon.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>) = state.anchorPosition
}
