package com.example.listfromapi.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.listfromapi.ui.theme.PokedexButton
import com.example.listfromapi.ui.theme.PokedexButtonBack
import com.example.listfromapi.viewModel.PokemonViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationController(navController: NavHostController, viewModel: PokemonViewModel){
    Scaffold(
        bottomBar = { BottomAppBar(actions = {
            NavigationBarItem(
                onClick = {},
                selected = false,
                icon = {
                    Icon(
                        painterResource(R.drawable.favorite),
                        contentDescription = "Home List",
                        modifier = Modifier.size(50.dp)
                            .clip(shape = RoundedCornerShape(20.dp))
                            .border(2.dp, Color.Black, shape = RoundedCornerShape(20.dp))
                            .background(PokedexButton)
                            .padding(5.dp)
                    )},
                label = {
                    Text("Favorite", color = Color.Black)
                }
            )},
            containerColor = PokedexButtonBack
        )}
    ){
        NavHost(navController, startDestination = Routes.ListScreen.route){
            composable(Routes.ListScreen.route) { ListScreen(viewModel, navController) }
            composable(Routes.Settings.route) { SettingsScreen() }
            composable(Routes.PokemonData.route) { ItemDataScreen(navController, viewModel) }
        }
    }
}