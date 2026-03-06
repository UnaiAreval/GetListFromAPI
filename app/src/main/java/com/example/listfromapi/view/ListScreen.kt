package com.example.listfromapi.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.listfromapi.R
import com.example.listfromapi.Routes
import com.example.listfromapi.ui.theme.AppColors
import com.example.listfromapi.viewModel.PokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController, pokemonViewModel: PokemonViewModel){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(AppColors.PokedexBack.value)
    ) {
        SearchBar(
            query = pokemonViewModel.pokeNameFilter.value,
            onQueryChange = { pokemonViewModel.onSearchTextChange(it) },
            onSearch = { pokemonViewModel.onSearch(it) },
            active = pokemonViewModel.active.value,
            onActiveChange = { pokemonViewModel.onActiveChange(it) },
            modifier = Modifier,
            placeholder = {Text("Search pokemon...")},
            leadingIcon = { R.drawable.pokemagnifingglass },
            trailingIcon = {
                if (pokemonViewModel.active.value) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.close),
                        contentDescription = "Tancar",
                        modifier = Modifier.clickable {
                            if (pokemonViewModel.pokeNameFilter.value.isNotEmpty()) {
                                pokemonViewModel.onSearchTextChange("")
                            } else {
                                pokemonViewModel.onActiveChange(false)
                            }
                        }
                    )
                }
            }
        ){
            if (pokemonViewModel.pokeNameFilter.value.isNotEmpty()) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(pokemonViewModel.filteredList) { pokemon ->
                        ResultatCard(nom = pokemon.name)
                    }
                }
            }
            else {
                if (pokemonViewModel.searchHistory.isNotEmpty()) {
                    Text(
                        text = "Cerques recents",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.primary
                    )

                    LazyColumn {
                        items(pokemonViewModel.searchHistory) { itemHistorial ->
                            ListItem(
                                headlineContent = { Text(text = itemHistorial) },
                                leadingContent = { Icon(painterResource(R.drawable.pokedex), contentDescription = null) },
                                modifier = Modifier.clickable {
                                    pokemonViewModel.onSearchTextChange(itemHistorial)
                                }
                            )
                        }
                        item {
                            TextButton(onClick = { pokemonViewModel.onClearHistory() }) {
                                Text("Clear previous search")
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.PokedexBack.value),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
fun ResultatCard(nom: String) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column {
            Text(
                text = nom,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Usuari registrat",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}