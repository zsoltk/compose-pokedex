package com.sudhindra.composepokedex.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sudhindra.composepokedex.room.models.FavouritePokemon
import com.sudhindra.composepokedex.room.typeconverters.PokemonTypeConverter

@Database(entities = [FavouritePokemon::class], version = 1, exportSchema = false)
@TypeConverters(PokemonTypeConverter::class)
abstract class FavouritePokemonDatabase : RoomDatabase() {

    abstract fun favouritePokemonDao(): FavouritePokemonDao
}
