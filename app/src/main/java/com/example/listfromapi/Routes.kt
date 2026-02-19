package com.example.listfromapi

import kotlinx.serialization.Serializable

sealed class Routes(val route: String) {
    object Settings: Routes("Settings")
    object ListScreen: Routes("List")
    object PokemonData: Routes("Pokemon")
}
