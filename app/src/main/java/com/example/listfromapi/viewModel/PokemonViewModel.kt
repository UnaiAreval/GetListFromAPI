package com.example.listfromapi.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.listfromapi.model.Pokemon
import com.example.listfromapi.network.PokeAPIclient
import com.example.listfromapi.network.retrofitBuilder
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel(){
    private val retrofit = retrofitBuilder().build()
    private val pokeApiRequest = retrofit.create(PokeAPIclient::class.java)
    val pokemonAmount = 151
    val pokeList: MutableList<Pokemon?> = mutableStateListOf()
    val pokemon: MutableState<Pokemon?> = mutableStateOf(null)
    val currentImage = mutableStateOf("")

    var pokeNameFilter = mutableStateOf("")
        private set
    var active = mutableStateOf(false)
    var searchHistory = mutableStateListOf<String>()
        private set
    val filteredList = mutableStateListOf<Pokemon>()

    fun onSearchTextChange(text: String) {
        pokeNameFilter.value = text

        if (text.isEmpty()) {
            filteredList.clear()
        } else {
            // Si hi ha text, filtrem la llista completa
            filteredList.clear()
            val results = pokeList.filter { pokemon ->
                // 'ignoreCase = true' fa que li sigui igual majúscules que minúscules
                if (pokemon != null) pokemon.name.contains(text, ignoreCase = true)
                else false
            }
            filteredList.addAll(results as Collection<Pokemon>)
        }
    }

    fun onActiveChange(isActive: Boolean) {
        active.value = isActive
        onSearchTextChange("")
        if (!isActive) {
            pokeNameFilter.value = ""
            filteredList.clear()
        }
    }

    fun onSearch(text: String) {
        if (text.isNotEmpty()) {
            if (searchHistory.size > 5){
                searchHistory.removeAt(0)
            }
            searchHistory.add(text)
        }
    }

    fun onClearHistory() {
        searchHistory.clear()
    }

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
    fun getPokemon(index: Int, travelToDataScreen: () -> Unit){
        pokemon.value = pokeList[index]
        currentImage.value = pokemon.value?.sprites?.front_default!!
        travelToDataScreen()
    }
}