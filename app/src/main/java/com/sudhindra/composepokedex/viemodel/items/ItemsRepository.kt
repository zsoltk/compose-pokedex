package com.sudhindra.composepokedex.viemodel.items

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.sudhindra.composepokedex.api.PokeApi
import com.sudhindra.composepokedex.constants.PaginationConstants
import com.sudhindra.composepokedex.paging.ItemsPagingSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class ItemsRepository @Inject constructor(
    private val api: PokeApi
) {
    fun getItems(scope: CoroutineScope) = Pager(
        config = PagingConfig(pageSize = PaginationConstants.PAGE_SIZE),
        pagingSourceFactory = { ItemsPagingSource(api, scope) }
    )
}
