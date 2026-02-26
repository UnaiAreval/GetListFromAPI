package com.example.listfromapi.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listfromapi.model.Pokemon
import com.example.listfromapi.network.PokeAPIclient
import com.example.listfromapi.network.retrofitBuilder
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel(){
    private val retrofit = retrofitBuilder().build()
    private val pokeApiRequest = retrofit.create(PokeAPIclient::class.java)
    val pokemonAmount = 151
    var pokeList: MutableList<Pokemon?> = mutableListOf()
    fun getPokemons(){
        viewModelScope.launch {
            try {
                val response = pokeApiRequest.getPokemonList("pokemon?offset=0&limit=${pokemonAmount}")

                if (response.isSuccessful){
                    val body = response.body()
                    for(i  in 0..body?.results!!.lastIndex) {
                        pokeList.add(pokeApiRequest.getPokemon(body.results[i].url).body())
                        Log.d("POKE_LOG", "Name: ${pokeList[pokeList.lastIndex]?.name}\nImage: ${pokeList[pokeList.lastIndex]?.sprites?.front_default}\nHeight: ${pokeList[pokeList.lastIndex]?.height}")
                    }
                }
            } catch (e: Exception){
                Log.e("POKE_LOG", "Error finding pokemon list")
            }
        }
    }
}