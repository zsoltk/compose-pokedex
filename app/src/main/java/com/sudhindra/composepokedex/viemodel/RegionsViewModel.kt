package com.sudhindra.composepokedex.viemodel

import androidx.lifecycle.ViewModel
import com.sudhindra.composepokedex.api.PokeApi
import com.sudhindra.composepokedex.models.region.Region
import com.sudhindra.composepokedex.utils.launch
import com.sudhindra.composepokedex.utils.splitAndCapitalise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RegionsViewModel @Inject constructor(
    private val api: PokeApi
) : ViewModel() {

    private val _regionsScreenState: MutableStateFlow<RegionsScreenState> =
        MutableStateFlow(RegionsScreenState.Loading)
    val regionsScreenState: StateFlow<RegionsScreenState> = _regionsScreenState

    fun getRegions() = launch {
        try {
            val regions = api.getRegions().body()!!.results.mapIndexed { index, t ->
                Region(t.regionIdFromUrl, t.name)
            }.map { it.copy(name = it.name.splitAndCapitalise()) }
            _regionsScreenState.value = RegionsScreenState.ShowingData(regions)
        } catch (e: Exception) {
            e.printStackTrace()
            _regionsScreenState.value = RegionsScreenState.Error("Failed to get Regions")
        }
    }
}

sealed class RegionsScreenState {
    object Loading : RegionsScreenState()
    data class ShowingData(val regions: List<Region>) : RegionsScreenState()
    data class Error(val msg: String) : RegionsScreenState()
}
