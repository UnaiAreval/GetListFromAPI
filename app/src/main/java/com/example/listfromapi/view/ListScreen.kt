package com.example.listfromapi.view

import android.app.Dialog
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
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.listfromapi.Routes
import com.example.listfromapi.ui.theme.AppColors
import com.example.listfromapi.viewModel.PokemonViewModel
import androidx.compose.ui.window.Dialog
import com.example.listfromapi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController, pokemonViewModel: PokemonViewModel){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(AppColors.PokedexBack.value)
    ) {
        ConstraintLayout(
            modifier = Modifier.background(AppColors.PokedexButtonBack.value).padding(10.dp).fillMaxWidth()
        ) {
            val (searchBar, buttonSearch) = createRefs()
            TextField(
                label = {Text("Search Pokemons by Name")},
                value = pokemonViewModel.pokeNameFilter.value,
                onValueChange = {pokemonViewModel.onSearchTextChange(it)},
                colors = TextFieldColors(
                    focusedTextColor = AppColors.TextColor.value,
                    unfocusedTextColor = AppColors.TextColor.value,
                    disabledTextColor = AppColors.TextColor.value,
                    errorTextColor = Color.Red,
                    focusedContainerColor = AppColors.PokedexData.value,
                    unfocusedContainerColor = AppColors.PokedexBack.value,
                    disabledContainerColor = AppColors.PokedexBack.value,
                    errorContainerColor = AppColors.PokedexBack.value,
                    cursorColor = AppColors.TextColor.value,
                    errorCursorColor = AppColors.TextColor.value,
                    focusedIndicatorColor = TextFieldDefaults.colors().focusedIndicatorColor,
                    unfocusedIndicatorColor = TextFieldDefaults.colors().unfocusedIndicatorColor,
                    disabledIndicatorColor = TextFieldDefaults.colors().disabledIndicatorColor,
                    errorIndicatorColor = TextFieldDefaults.colors().errorIndicatorColor,
                    focusedLeadingIconColor = TextFieldDefaults.colors().focusedLeadingIconColor,
                    unfocusedLeadingIconColor = TextFieldDefaults.colors().unfocusedLeadingIconColor,
                    disabledLeadingIconColor = TextFieldDefaults.colors().disabledLeadingIconColor,
                    errorLeadingIconColor = TextFieldDefaults.colors().errorLeadingIconColor,
                    focusedTrailingIconColor = TextFieldDefaults.colors().focusedTrailingIconColor,
                    unfocusedTrailingIconColor = TextFieldDefaults.colors().unfocusedTrailingIconColor,
                    disabledTrailingIconColor = TextFieldDefaults.colors().disabledTrailingIconColor,
                    errorTrailingIconColor = TextFieldDefaults.colors().errorTrailingIconColor,
                    focusedLabelColor = TextFieldDefaults.colors().focusedLabelColor,
                    unfocusedLabelColor = TextFieldDefaults.colors().unfocusedLabelColor,
                    disabledLabelColor = TextFieldDefaults.colors().disabledLabelColor,
                    errorLabelColor = TextFieldDefaults.colors().errorLabelColor,
                    focusedPlaceholderColor = TextFieldDefaults.colors().focusedPlaceholderColor,
                    unfocusedPlaceholderColor = TextFieldDefaults.colors().unfocusedPlaceholderColor,
                    disabledPlaceholderColor = TextFieldDefaults.colors().disabledPlaceholderColor,
                    errorPlaceholderColor = TextFieldDefaults.colors().errorPlaceholderColor,
                    focusedSupportingTextColor = TextFieldDefaults.colors().focusedSupportingTextColor,
                    unfocusedSupportingTextColor = TextFieldDefaults.colors().unfocusedSupportingTextColor,
                    disabledSupportingTextColor = TextFieldDefaults.colors().disabledSupportingTextColor,
                    errorSupportingTextColor = TextFieldDefaults.colors().errorSupportingTextColor,
                    focusedPrefixColor = TextFieldDefaults.colors().focusedPrefixColor,
                    unfocusedPrefixColor = TextFieldDefaults.colors().unfocusedPrefixColor,
                    disabledPrefixColor = TextFieldDefaults.colors().disabledPrefixColor,
                    errorPrefixColor = TextFieldDefaults.colors().errorPrefixColor,
                    focusedSuffixColor = TextFieldDefaults.colors().focusedSuffixColor,
                    unfocusedSuffixColor = TextFieldDefaults.colors().unfocusedSuffixColor,
                    disabledSuffixColor = TextFieldDefaults.colors().disabledSuffixColor,
                    errorSuffixColor = TextFieldDefaults.colors().errorSuffixColor,
                    textSelectionColors = TextFieldDefaults.colors().textSelectionColors
                ),
                modifier = Modifier.border(2.dp, AppColors.TextColor.value)
                    .constrainAs(searchBar){
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(buttonSearch.start)
                    }
            )
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(AppColors.PokedexButton.value, RoundedCornerShape(20.dp))
                    .clickable{
                        pokemonViewModel.onSearch()
                        pokemonViewModel.onActiveChange(true)

                    }
                    .constrainAs(buttonSearch){
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(searchBar.end)
                    }
            ){
                Icon(
                    painter = painterResource(R.drawable.pokemagnifingglass),
                    contentDescription ="Search",
                    tint = AppColors.TextColor.value,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(10.dp)
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.PokedexBack.value),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            items(pokemonViewModel.pokeList) { pokemon ->
                if (pokemon != null) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .clip(shape = RoundedCornerShape(20.dp))
                            .border(2.dp, AppColors.PokedexBack.value, RoundedCornerShape(20.dp))
                            .background(AppColors.PokedexData.value)
                            .clickable {
                                pokemonViewModel.getPokemon(
                                    index = pokemon.id - 1,
                                    travelToDataScreen = { navController.navigate(Routes.PokemonData.route) })
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
                                .border(
                                    2.dp,
                                    AppColors.PokedexBack.value,
                                    RoundedCornerShape(50.dp)
                                )
                                .constrainAs(image) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start, margin = 10.dp)
                                }
                        )
                        Text(
                            text = "Nº ${pokemon.id}",
                            fontSize = 20.sp,
                            modifier = Modifier.constrainAs(id) {
                                start.linkTo(image.end, margin = 15.dp)
                                top.linkTo(image.top)
                                bottom.linkTo(image.bottom, margin = 20.dp)
                            }
                        )
                        Text(
                            text = pokemon.name,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.constrainAs(name) {
                                start.linkTo(id.start, margin = 10.dp)
                                top.linkTo(id.bottom, margin = 5.dp)
                            }
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

    }
    if (pokemonViewModel.active.value) SearchResults(pokemonViewModel, navController)
}

@Composable
fun LoadingList(navController: NavController, pokemonViewModel: PokemonViewModel){
    val progress = remember {mutableStateOf(0f)}

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.PokedexButtonBack.value)
    ) {
        CircularProgressIndicator(
            progress = { progress.value },
            modifier = Modifier
                .align(Alignment.Center)
                .border(5.dp, AppColors.PokedexBorder.value, CircleShape)
                .padding(10.dp)
                .size(150.dp)
                .background(AppColors.PokedexBack.value, CircleShape),
            color = AppColors.PokedexData.value,
            strokeWidth = 25.dp,
            trackColor = AppColors.PokedexBack.value,
            strokeCap = StrokeCap.Round
        )
    }
    if (pokemonViewModel.pokeList.size == pokemonViewModel.pokemonAmount) navController.navigate(Routes.LoadingScreen.route)
    else progress.value = ((pokemonViewModel.pokeList.size / pokemonViewModel.pokemonAmount) * 100).toFloat()
}

@Composable
fun SearchResults(pViewModel: PokemonViewModel, navCont: NavController) {
    Dialog(
        onDismissRequest = { pViewModel.onActiveChange(false) },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = true)
    ){
        Column(
            modifier = Modifier
                .fillMaxHeight(0.8F)
                .fillMaxWidth(0.7f)
                .background(
                    color = AppColors.PokedexBack.value,
                    shape = CutCornerShape(topStart = 2.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 2.dp)
                )
                .border(
                    width = 10.dp,
                    shape = CutCornerShape(topStart = 2.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 2.dp),
                    color =  AppColors.PokedexBorder.value
                )
                .padding(10.dp)
        ) {
            Box(modifier = Modifier
                .padding(10.dp)
                .background(AppColors.PokedexData.value, RoundedCornerShape(20.dp))
                .padding(5.dp)
                .align(Alignment.CenterHorizontally)
            ){
                Text(
                    text = "Results: ",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = AppColors.TextColor.value,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            LazyColumn(
                modifier = Modifier
            ) {
                items(pViewModel.filteredList){ p ->

                    ConstraintLayout(
                        modifier = Modifier
                            .clickable{ pViewModel.getPokemon(
                                index = p.id - 1,
                                travelToDataScreen = { navCont.navigate(Routes.PokemonData.route) })
                            }
                    ) {
                        val (image, name) = createRefs()
                        AsyncImage(
                            model = p.sprites.front_default,
                            contentDescription = p.name + " front image",
                            modifier = Modifier
                                .size(75.dp)
                                .clip(shape = RoundedCornerShape(50.dp))
                                .border(
                                    2.dp,
                                    AppColors.PokedexBack.value,
                                    RoundedCornerShape(50.dp)
                                )
                                .constrainAs(image) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start, margin = 10.dp)
                                }
                        )
                        Text(p.name, modifier = Modifier.constrainAs(name){
                            top.linkTo(image.top, margin = 5.dp)
                            start.linkTo(image.end, margin = 10.dp)
                            bottom.linkTo(image.bottom)
                        })
                    }
                }
            }
        }
    }
}