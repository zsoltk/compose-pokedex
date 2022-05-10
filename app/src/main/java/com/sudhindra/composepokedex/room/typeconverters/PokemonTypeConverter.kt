package com.sudhindra.composepokedex.room.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sudhindra.composepokedex.models.pokemon.Pokemon

object PokemonTypeConverter {
    @TypeConverter
    fun stringToPokemon(value: String?): Pokemon? =
        value?.let { Gson().fromJson(it, Pokemon::class.java) }

    @TypeConverter
    fun pokemonToString(pokemon: Pokemon?): String? = pokemon?.let { Gson().toJson(it) }
}
