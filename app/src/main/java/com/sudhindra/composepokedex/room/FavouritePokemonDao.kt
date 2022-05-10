package com.sudhindra.composepokedex.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sudhindra.composepokedex.room.models.FavouritePokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritePokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: FavouritePokemon)

    @Update
    suspend fun update(pokemon: FavouritePokemon)

    @Delete
    suspend fun delete(pokemon: FavouritePokemon)

    @Query("SELECT * FROM favourite_pokemon_table")
    fun getFavouritePokemon(): Flow<List<FavouritePokemon>>
}
