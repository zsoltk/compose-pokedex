package com.sudhindra.composepokedex.viemodel

import androidx.lifecycle.ViewModel
import com.sudhindra.composepokedex.api.PokeApi
import com.sudhindra.composepokedex.models.type.Type
import com.sudhindra.composepokedex.utils.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TypesViewModel @Inject constructor(
    private val api: PokeApi
) : ViewModel() {

    private val _typesScreenState: MutableStateFlow<TypesScreenState> =
        MutableStateFlow(TypesScreenState.Loading)
    val typesScreenState: StateFlow<TypesScreenState> = _typesScreenState

    fun getTypes() = launch {
        try {
            val types = api.getTypes().body()!!.results.mapIndexed { index, t ->
                Type(t.typeIdFromUrl, t.name)
            }.map { it.copy(name = it.formattedName) }
            _typesScreenState.value = TypesScreenState.ShowingData(types)
        } catch (e: Exception) {
            e.printStackTrace()
            _typesScreenState.value = TypesScreenState.Error("Failed to get Types")
        }
    }
}

sealed class TypesScreenState {
    object Loading : TypesScreenState()
    data class ShowingData(val types: List<Type>) : TypesScreenState()
    data class Error(val msg: String) : TypesScreenState()
}
