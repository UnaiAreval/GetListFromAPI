package com.example.listfromapi.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.listfromapi.R
import com.example.listfromapi.Routes
import com.example.listfromapi.ui.theme.AppColors
import com.example.listfromapi.viewModel.PokemonViewModel

@Composable
fun NavigationController(navController: NavHostController, viewModel: PokemonViewModel){
    Scaffold(
        bottomBar = {
            BottomAppBar(actions = {
                NavigationBarItem(
                    onClick = {},
                    selected = false,
                    icon = {
                        Icon(
                            painterResource(R.drawable.favorite),
                            contentDescription = "Favorite List",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(shape = RoundedCornerShape(20.dp))
                                .border(2.dp, Color.Black, shape = RoundedCornerShape(20.dp))
                                .background(AppColors.PokedexButton.value)
                                .padding(5.dp)
                        )},
                    label = {
                        Text("Favorite", color = AppColors.TextColor.value)
                    }
                )
                NavigationBarItem(
                    onClick = {
                        navController.navigate(Routes.Settings.route)
                    },
                    selected = false,
                    icon = {
                        Icon(
                            painterResource(R.drawable.pokemon_settings),
                            contentDescription = "Settings",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(shape = RoundedCornerShape(20.dp))
                                .border(2.dp, Color.Black, shape = RoundedCornerShape(20.dp))
                                .background(AppColors.PokedexButton.value)
                                .padding(5.dp)
                        )
                    },
                    label = {
                        Text("Settings", color = AppColors.TextColor.value)
                    }
                )
            },
                modifier = Modifier.fillMaxHeight(0.15f),
            containerColor = AppColors.PokedexButtonBack.value)
        }
    ){ paddingValues ->
        NavHost(navController, startDestination = Routes.ListScreen.route, modifier = Modifier.padding(paddingValues)) {
            composable(Routes.ListScreen.route) { ListScreen(navController, viewModel) }
            composable(Routes.Settings.route) { SettingsScreen() }
            composable(Routes.PokemonData.route) {
                ItemDataScreen(
                    navController,
                    viewModel
                )
            }
        }
    }
}