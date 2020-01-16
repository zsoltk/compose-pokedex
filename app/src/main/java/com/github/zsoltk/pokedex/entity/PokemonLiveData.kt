package com.github.zsoltk.pokedex.entity

import androidx.lifecycle.MutableLiveData
import com.github.zsoltk.pokedex.common.AsyncState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class PokemonLiveData : MutableLiveData<AsyncState<List<Pokemon>>>() {

    init {
        reload()
    }

    private var disposable: Disposable? = null

    fun reload() {
        disposable?.dispose()
        disposable = PokemonApi
            .loadPokemon()
            .observeOn(AndroidSchedulers.mainThread())
            .map { AsyncState.Result(it) as AsyncState<List<Pokemon>> }
            .startWith(AsyncState.Loading())
            .onErrorReturn { AsyncState.Error(it) }
            .subscribe { value = it }
    }
}
