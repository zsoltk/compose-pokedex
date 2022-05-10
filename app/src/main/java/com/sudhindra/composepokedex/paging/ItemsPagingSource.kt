package com.sudhindra.composepokedex.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sudhindra.composepokedex.api.PokeApi
import com.sudhindra.composepokedex.constants.PaginationConstants
import com.sudhindra.composepokedex.models.item.Item
import com.sudhindra.composepokedex.utils.splitAndCapitalise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import retrofit2.HttpException
import java.io.IOException

class ItemsPagingSource(
    private val api: PokeApi,
    private val scope: CoroutineScope
) : PagingSource<Int, Item>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val page = params.key ?: 1
        val offset = (page - 1) * PaginationConstants.PAGE_SIZE

        return try {
            val response = api.getItems(offset, PaginationConstants.PAGE_SIZE)
            val results = response.body()!!.results
            val items =
                results.map { scope.async { api.getItemDetails(it.name).body()!! } }
                    .awaitAll()
                    .map { it.copy(name = it.name.splitAndCapitalise()) }

            LoadResult.Page(
                items,
                if (page == 1) null else page - 1,
                if (items.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>) = state.anchorPosition
}
