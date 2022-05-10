package com.sudhindra.composepokedex.api

import com.sudhindra.composepokedex.models.Pokedex
import com.sudhindra.composepokedex.models.item.Item
import com.sudhindra.composepokedex.models.location.Location
import com.sudhindra.composepokedex.models.pokemon.EvolutionChain
import com.sudhindra.composepokedex.models.pokemon.Pokemon
import com.sudhindra.composepokedex.models.pokemon.PokemonSpecies
import com.sudhindra.composepokedex.models.pokemon.SearchResult
import com.sudhindra.composepokedex.models.region.RegionInfo
import com.sudhindra.composepokedex.models.type.TypeInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeApi {

    // Pokemon
    @GET("pokemon")
    suspend fun getAllPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<SearchResult>

    @GET("pokemon/{name}/")
    suspend fun getPokemonDetails(@Path("name") name: String): Response<Pokemon>

    @GET("pokemon/{id}/")
    suspend fun getPokemonDetails(@Path("id") id: Int): Response<Pokemon>

    @GET("pokemon-species/{id}/")
    suspend fun getSpecies(@Path("id") id: Int): Response<PokemonSpecies>

    @GET
    suspend fun getEvolutionChain(@Url url: String): Response<EvolutionChain>

    // Pokedex
    @GET
    suspend fun getPokedexInfo(@Url url: String): Response<Pokedex>

    // Items
    @GET("item")
    suspend fun getItems(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<SearchResult>

    @GET("item/{name}/")
    suspend fun getItemDetails(@Path("name") name: String): Response<Item>

    // Locations
    @GET("location")
    suspend fun getLocations(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<SearchResult>

    @GET("location/{name}/")
    suspend fun getLocationDetails(@Path("name") name: String): Response<Location>

    // Types
    @GET("type")
    suspend fun getTypes(): Response<SearchResult>

    @GET("type/{id}/")
    suspend fun getTypeInfo(@Path("id") id: Int): Response<TypeInfo>

    // Regions
    @GET("region")
    suspend fun getRegions(): Response<SearchResult>

    @GET("region/{id}")
    suspend fun getRegionInfo(@Path("id") id: Int): Response<RegionInfo>
}
