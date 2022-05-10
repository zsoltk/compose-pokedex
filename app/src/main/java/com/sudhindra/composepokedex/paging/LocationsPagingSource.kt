package com.sudhindra.composepokedex.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sudhindra.composepokedex.api.PokeApi
import com.sudhindra.composepokedex.constants.PaginationConstants
import com.sudhindra.composepokedex.models.location.Location
import com.sudhindra.composepokedex.utils.splitAndCapitalise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import retrofit2.HttpException
import java.io.IOException

class LocationsPagingSource(
    private val api: PokeApi,
    private val scope: CoroutineScope
) : PagingSource<Int, Location>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
        val page = params.key ?: 1
        val offset = (page - 1) * PaginationConstants.PAGE_SIZE

        return try {
            val response = api.getLocations(offset, PaginationConstants.PAGE_SIZE)
            val results = response.body()!!.results
            val locations =
                results.map { scope.async { api.getLocationDetails(it.name).body()!! } }
                    .awaitAll()
                    .map { it.copy(name = it.name.splitAndCapitalise()) }

            LoadResult.Page(
                locations,
                if (page == 1) null else page - 1,
                if (locations.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Location>) = state.anchorPosition
}
