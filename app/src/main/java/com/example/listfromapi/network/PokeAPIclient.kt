package com.example.listfromapi.network

import com.example.listfromapi.model.Pokemon
import retrofit2.Response
import retrofit2.http.GET

interface PokeAPIclient {
    @GET("pokemon/Zekrom/")
    suspend fun getPokemon(): Response<Pokemon>

    @GET(" ")
    suspend fun getPokemonList(): Response<List<Pokemon>>
    //@GET("pokemon/{po}}/") To give it the po parameter and then it will ask for the pokemon with that parameter
}