package com.example.listfromapi.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.listfromapi.viewModel.PokemonViewModel

@Composable
fun ListScreen(pokemonViewModel: PokemonViewModel, navController: NavController){
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        for (p in pokemonViewModel.pokeList){
            item {
                ConstraintLayout {
                    val (image, name, id) = createRefs()
                }
            }
        }
    }
}