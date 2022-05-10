package com.sudhindra.composepokedex.paging.pokemon

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sudhindra.composepokedex.api.PokeApi
import com.sudhindra.composepokedex.constants.PaginationConstants
import com.sudhindra.composepokedex.models.pokemon.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import retrofit2.HttpException
import java.io.IOException

class AllPokemonPagingSource(
    private val api: PokeApi,
    private val scope: CoroutineScope
) : PagingSource<Int, Pokemon>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page = params.key ?: 1
        val offset = (page - 1) * PaginationConstants.PAGE_SIZE

        return try {
            val results = api.getAllPokemon(offset, PaginationConstants.PAGE_SIZE).body()!!.results
            val pokemon =
                results.map { scope.async { api.getPokemonDetails(it.name).body()!! } }
                    .awaitAll()

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
