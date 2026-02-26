package com.example.listfromapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.listfromapi.ui.theme.ListFromAPITheme
import com.example.listfromapi.view.NavigationController
import com.example.listfromapi.viewModel.PokemonViewModel
import kotlinx.coroutines.delay
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ListFromAPITheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navigationController = rememberNavController()
                    val pokeView: PokemonViewModel by viewModels()
                    pokeView.getPokemons()
                    NavigationController(navigationController, pokeView)
                }
            }
        }
    }
}