package com.example.listfromapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.listfromapi.data.SettingsRepo
import com.example.listfromapi.ui.theme.AppColors
import com.example.listfromapi.ui.theme.ListFromAPITheme
import com.example.listfromapi.view.NavigationController
import com.example.listfromapi.viewModel.PokemonViewModel
import com.example.listfromapi.viewModel.SettingsViewModel
import com.example.listfromapi.viewModel.SettingsViewModelFactory
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ListFromAPITheme {
                val context = LocalContext.current
                val repository = remember { SettingsRepo(context) }
                val settingsViewModel: SettingsViewModel = viewModel(
                    factory = SettingsViewModelFactory(repository)
                )

                AppColors.changeAppColors(settingsViewModel.colorPalette)
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navigationController = rememberNavController()
                    val pokeView: PokemonViewModel by viewModels()
                    NavigationController(navigationController, pokeView)
                }
            }
        }
    }
}