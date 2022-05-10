package com.sudhindra.composepokedex.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.sudhindra.composepokedex.models.pokemon.Pokemon
import com.sudhindra.composepokedex.room.typeconverters.PokemonTypeConverter

@Entity(tableName = "favourite_pokemon_table")
data class FavouritePokemon(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @TypeConverters(PokemonTypeConverter::class)
    val pokemon: Pokemon
)
