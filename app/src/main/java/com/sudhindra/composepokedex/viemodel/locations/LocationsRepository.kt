package com.sudhindra.composepokedex.viemodel.locations

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.sudhindra.composepokedex.api.PokeApi
import com.sudhindra.composepokedex.constants.PaginationConstants
import com.sudhindra.composepokedex.paging.LocationsPagingSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class LocationsRepository @Inject constructor(
    private val api: PokeApi
) {
    fun getLocations(scope: CoroutineScope) = Pager(
        config = PagingConfig(pageSize = PaginationConstants.PAGE_SIZE),
        pagingSourceFactory = { LocationsPagingSource(api, scope) }
    )
}
