package com.sudhindra.composepokedex.ui.paging

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.sudhindra.composepokedex.ui.components.Placeholder
import com.sudhindra.composepokedex.ui.components.RetryButton

@Composable
fun <T : Any> LazyPagingColumn(
    modifier: Modifier,
    lazyPagingItems: LazyPagingItems<T>,
    pagingItemComposable: @Composable (T) -> Unit
) {
    LazyColumn(modifier) {
        item {
            when (lazyPagingItems.loadState.refresh) {
                is LoadState.NotLoading -> {
                }
                LoadState.Loading -> Placeholder(Modifier.fillParentMaxSize())
                // TODO: Keeps trying to Refresh even without clicking
                is LoadState.Error -> RetryButton(
                    Modifier.fillParentMaxSize(),
                    onClick = lazyPagingItems::refresh
                )
            }
        }

        items(lazyPagingItems) { item ->
            if (item != null)
                pagingItemComposable(item)
        }

        item {
            Crossfade(lazyPagingItems.loadState.append) { append ->
                when (append) {
                    // TODO: Need to test this case
                    is LoadState.NotLoading -> if (!append.endOfPaginationReached) lazyPagingItems.retry()
                    LoadState.Loading -> Placeholder(
                        Modifier
                            .fillMaxWidth()
                            .padding(30.dp)
                    )
                    is LoadState.Error -> RetryButton(
                        Modifier
                            .fillMaxWidth()
                            .padding(30.dp),
                        onClick = lazyPagingItems::retry
                    )
                }
            }
        }
    }
}
