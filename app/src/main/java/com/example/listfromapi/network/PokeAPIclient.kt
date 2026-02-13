package com.example.listfromapi.network

import com.example.listfromapi.model.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PokeAPIclient {
    @GET("pokemon/Zekrom/")
    suspend fun getPokemon(): Response<Pokemon>

    @GET("pokemon/")
    suspend fun getPokemonList(): Response<List<Pokemon>>

    @GET()
    suspend fun getPokemon(@Url url: String): Response<Pokemon>
}