package com.github.zsoltk.pokedex.entity

import androidx.annotation.FloatRange
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

object PokemonApi {

    /**
     * Just for demonstration purposes.
     * You could do the same with a proper API.
     * You could also do the same with LiveData if you wish.
     */
    fun loadPokemon(): Observable<List<Pokemon>> =
        Observable
            .just(pokemons)
            .delay(200, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .map {
                if (Random.nextFloat() < randomFailureChance) throw FakeApiException()
                it
            }


    /**
     * Feel free to try different values to test error screen
     */
    @FloatRange(from = 0.0, to = 1.0)
    val randomFailureChance: Float = 0.1f

    class FakeApiException : RuntimeException("Test exception, please ignore")
}
