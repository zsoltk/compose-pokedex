package com.sudhindra.composepokedex.viemodel.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    repository: ItemsRepository,
) : ViewModel() {
    var itemsPager = repository.getItems(viewModelScope)
}
