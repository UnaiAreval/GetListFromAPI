package com.example.listfromapi.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.listfromapi.Routes
import com.example.listfromapi.ui.theme.AppColors
import com.example.listfromapi.viewModel.PokemonViewModel

@Composable
fun ListScreen(navController: NavController, pokemonViewModel: PokemonViewModel){
    LaunchedEffect(Unit) {
        pokemonViewModel.getPokemonList()
    }
    val pokeList = pokemonViewModel.pokeList

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.PokedexBack.value)
    ) {
        items(pokeList){ pokemon ->
            if (pokemon != null) {
                ConstraintLayout(
                    modifier = Modifier
                        .background(AppColors.PokedexData.value)
                        .clickable {
                            pokemonViewModel.getPokemon(pokemon.id)
                            navController.navigate(Routes.PokemonData.route)
                        }
                        .padding(10.dp)
                ) {
                    val (image, name, id) = createRefs()
                    AsyncImage(
                        model = pokemon.sprites.front_default,
                        contentDescription = pokemon.name + " front image",
                        modifier = Modifier
                            .fillMaxSize(0.05f)
                            .clip(RoundedCornerShape(20.dp))
                            .border(2.dp, AppColors.PokedexBack.value, RoundedCornerShape(20.dp))
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start, margin = 10.dp)
                            }
                    )
                    Text(
                        text = "Nº ${pokemon.id}",
                        fontSize = 15.sp,
                        modifier = Modifier.constrainAs(id) {
                            start.linkTo(image.end, margin = 15.dp)
                            top.linkTo(image.top, margin = 10.dp)
                        },
                        color = AppColors.TextColor.value
                    )
                    Text(
                        text = pokemon.name,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(name) {
                            start.linkTo(id.end, margin = 10.dp)
                            top.linkTo(id.bottom, margin = 5.dp)
                        },
                        color = AppColors.TextColor.value
                    )
                }
            }
        }
    }
}