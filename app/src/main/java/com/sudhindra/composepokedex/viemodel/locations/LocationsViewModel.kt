package com.sudhindra.composepokedex.viemodel.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    repository: LocationsRepository,
) : ViewModel() {
    var locationsPager = repository.getLocations(viewModelScope)
}
