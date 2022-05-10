package com.sudhindra.composepokedex.di

import android.app.Application
import androidx.room.Room
import com.sudhindra.composepokedex.room.FavouritePokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideFavouritePokemonDatabase(application: Application) =
        Room.databaseBuilder(
            application,
            FavouritePokemonDatabase::class.java,
            "favourite_pokemon_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideFavouritePokemonDao(db: FavouritePokemonDatabase) = db.favouritePokemonDao()
}
