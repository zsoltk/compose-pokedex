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

class RegionalPokemonPagingSource(
    private val api: PokeApi,
    private val scope: CoroutineScope,
    private val regionId: Int
) : PagingSource<Int, Pokemon>() {

    private var ids = listOf<Int>()
    private var pokedexUrls = listOf<String>()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page = params.key ?: 1
        val offset = (page - 1) * PaginationConstants.PAGE_SIZE

        return try {
            if (ids.isEmpty()) {
                val pokedexes = api.getRegionInfo(regionId).body()!!.pokedexes
                var updatedDexes =
                    pokedexes.filter { it.name.contains("updated") || it.name.contains("extended") }
                if (updatedDexes.isEmpty()) {
                    updatedDexes = pokedexes
                }
                pokedexUrls = updatedDexes.map { it.url }

                ids = pokedexUrls.flatMap { url ->
                    val dexInfo = api.getPokedexInfo(url).body()!!
                    dexInfo.pokemonEntries.map { it.pokemonSpecies.pokemonSpeciesIdFromUrl }
                }
                // Remove Duplicates
                ids = ids.distinct()
            }

            if (offset > ids.lastIndex)
                return LoadResult.Page(
                    listOf(),
                    if (page == 1) null else page - 1,
                    null
                )

            val toIndex =
                if (offset + PaginationConstants.PAGE_SIZE <= ids.lastIndex) offset + PaginationConstants.PAGE_SIZE else ids.size
            val pokemon = ids.subList(offset, toIndex)
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
