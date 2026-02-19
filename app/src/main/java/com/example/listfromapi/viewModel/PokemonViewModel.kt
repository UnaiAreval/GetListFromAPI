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
    var pokemon: MutableState<Pokemon?> = mutableStateOf(null)
    var pokeList: MutableList<Pokemon?> = mutableListOf()

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
                    if (body?.sprites == null) Log.e("POKE_LOG", "Error finding ${body?.name}s images")
                    else Log.d("POKE_LOG", "Front image URL: ${body.sprites.front_default}")
                    if (body?.stats[0]?.stat?.name == null) Log.e("POKE_LOG", "Error finding ${body?.name}s stats")
                    else Log.d("POKE_LOG", "Stat ${body.stats[0].stat.name}: ${body.stats[0].base_stat}")
                    pokemon.value = body
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

                pokeList = mutableListOf()
                if (response.isSuccessful){
                    val body = response.body()
                    for(i  in 0..body?.results!!.lastIndex) {
                        body.results[i].id = getPokemonIdFromUrl(body.results[i].url)
                        Log.d("POKE_LOG", "Pokedex Num. ${body.results[i].id}")
                        getPokemon(body.results[i].id)
                        pokeList.add(pokemon.value)
                    }
                }
            } catch (e: Exception){
                Log.e("POKE_LOG", "An error occurred while trying to get the pokemon list: \n    ${e.message}")
            }
        }
    }

    fun getPokemon(id: Int){
        viewModelScope.launch {
            try {
                val response = pokeApiRequest.getPokemon("pokemon/$id/")
                if (response.isSuccessful){
                    val body = response.body()
                    pokemon.value = body
                    Log.d("POKE_LOG", "Pokedex Num. ${body?.id}     Name: ${body?.name}")
                    if (body?.sprites == null) Log.e("POKE_LOG", "Error finding ${body?.name}s images")
                    else Log.d("POKE_LOG", "Front image URL: ${body.sprites.front_default}")
                    if (body?.stats[0]?.stat?.name == null) Log.e("POKE_LOG", "Error finding ${body?.name}s stats")
                    else Log.d("POKE_LOG", "Stat ${body.stats[0].stat.name}: ${body.stats[0].base_stat}")
                }
            } catch (e: Exception){
                Log.e("POKE_LOG", "Error finding the pokemon $id")
            }
        }
    }
}

fun getPokemonIdFromUrl(url: String): Int{
    var pID = ""
    for (i in 34..<url.length - 1){
        pID += url[i]
    }

    return pID.toInt()
}