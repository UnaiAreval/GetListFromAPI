package com.example.listfromapi.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listfromapi.network.PokeAPIclient
import com.example.listfromapi.network.retrofitBuilder
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel(){
    private val retrofit = retrofitBuilder().build()
    private val pokeApiRequest = retrofit.create(PokeAPIclient::class.java)

    fun getZekrom(){
        viewModelScope.launch {
            try {
                val response = pokeApiRequest.getPokemon()

                if (response.isSuccessful){
                    val body = response.body()
                    Log.d("POKE_LOG", "Success")
                    Log.d("POKE_LOG", "Pokedex num. ${body?.id}")
                    Log.d("POKE_LOG", "Pokemon name: ${body?.name}")
                    Log.d("POKE_LOG", "Height: ${body?.height}     Weight: ${body?.weight}")
                    if (body?.pokemonImages == null) Log.e("POKE_LOG", "Error finding ${body?.name}s images")
                    else Log.d("POKE_LOG", "Front image URL: ${body.pokemonImages.front}")
                    if (body?.stats[0]?.statType?.name == null) Log.e("POKE_LOG", "Error finding ${body?.name}s stats")
                    else Log.d("POKE_LOG", "Stat ${body.stats[0].statType.name}: ${body.stats[0].baseStat}")
                }
            } catch (e: Exception) {
                Log.e("POKE_LOG", "An error occurred while finding the pokemon: \n    ${e.message}")
            }
        }
    }

    fun getPokemonList(){
        viewModelScope.launch {
            try {
                val response = pokeApiRequest.getPokemonList()

                if (response.isSuccessful){
                    val body = response.body()
                    Log.d("POKE_LOG", "Pokedex Num. ${body?.get(0)?.id}     Name: ${body?.get(0)?.name}")
                }
            } catch (e: Exception){
                Log.e("POKE_LOG", "An error occurred while trying to get the pokemon list: \n    ${e.message}")
            }
        }
    }
}