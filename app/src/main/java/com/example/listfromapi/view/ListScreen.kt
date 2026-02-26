package com.example.listfromapi.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.listfromapi.Routes
import com.example.listfromapi.ui.theme.PokedexBack
import com.example.listfromapi.ui.theme.PokedexBorder
import com.example.listfromapi.ui.theme.PokedexButtonBack
import com.example.listfromapi.ui.theme.PokedexData
import com.example.listfromapi.viewModel.PokemonViewModel

@Composable
fun ListScreen(navController: NavController, pokemonViewModel: PokemonViewModel){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(PokedexBack),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            Spacer(modifier = Modifier.height(10.dp))
        }
        items(pokemonViewModel.pokeList){ pokemon ->
            if (pokemon != null){
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .border(2.dp, PokedexBack, RoundedCornerShape(20.dp))
                        .background(PokedexData)
                        .clickable{
                            navController.navigate(Routes.PokemonData.route)
                        }
                        .padding(10.dp)
                ) {
                    val (image, name, id) = createRefs()
                    AsyncImage(
                        model = pokemon.sprites.front_default,
                        contentDescription = pokemon.name + " front image",
                        modifier = Modifier
                            .size(75.dp)
                            .clip(shape = RoundedCornerShape(50.dp))
                            .border(2.dp, PokedexBack, RoundedCornerShape(50.dp))
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start, margin = 10.dp)
                            }
                    )
                    Text(
                        text = "Nº ${pokemon.id}",
                        fontSize = 20.sp,
                        modifier = Modifier.constrainAs(id){
                            start.linkTo(image.end, margin = 15.dp)
                            top.linkTo(image.top)
                            bottom.linkTo(image.bottom, margin = 20.dp)
                        }
                    )
                    Text(
                        text = pokemon.name,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(name){
                            start.linkTo(id.start, margin = 10.dp)
                            top.linkTo(id.bottom, margin = 5.dp)
                        }
                    )
                }
            }
        }
        item{
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun LoadingList(navController: NavController, pokemonViewModel: PokemonViewModel){
    val progress = remember {mutableStateOf(0f)}

    Box(
        modifier = Modifier.fillMaxSize().background(PokedexButtonBack)
    ) {
        CircularProgressIndicator(
            progress = { progress.value },
            modifier = Modifier
                .align(Alignment.Center)
                .border(5.dp, PokedexBorder, CircleShape)
                .padding(10.dp)
                .size(150.dp)
                .background(PokedexBack, CircleShape),
            color = PokedexData,
            strokeWidth = 25.dp,
            trackColor = PokedexBack,
            strokeCap = StrokeCap.Round
        )
    }
    if (pokemonViewModel.pokeList.size == pokemonViewModel.pokemonAmount) navController.navigate(Routes.LoadingScreen.route)
    else progress.value = ((pokemonViewModel.pokeList.size / pokemonViewModel.pokemonAmount) * 100).toFloat()
}