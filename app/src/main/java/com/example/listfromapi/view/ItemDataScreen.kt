package com.example.listfromapi.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModel
import com.example.listfromapi.model.Pokemon

@Composable
fun ItemDataScreen(pokemon: Pokemon, viewModel: ViewModel){
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
    ){
        val (header, image, changeImage, nameBox, pokeData) = createRefs()

        Box(

        ){
            Text("Go Back")
        }
    }
}