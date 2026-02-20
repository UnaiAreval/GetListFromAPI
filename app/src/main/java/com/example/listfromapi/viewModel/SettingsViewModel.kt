package com.example.listfromapi.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listfromapi.data.SettingsRepo
import com.example.listfromapi.ui.theme.ColorPalette
import kotlin.jvm.java

class SettingsViewModel(private val repository: SettingsRepo) : ViewModel() {
    var colorPalette by mutableStateOf(repository.getColorPalette())
        private set

    fun updateColorPalette(newColorPalette: ColorPalette){
        colorPalette = newColorPalette
        repository.safeLocalData("color_palette", colorPalette.name)
    }

    var nomUsuari by mutableStateOf(repository.obtenirNom())
        private set

    fun actualitzarNom(nouNom: String) {
        nomUsuari = nouNom
        repository.safeLocalData("nom_usuari", nouNom)
    }
}

// Aquesta classe és el que guarda la peça que necessitem (el repository)
class SettingsViewModelFactory(private val repository: SettingsRepo) : ViewModelProvider.Factory {

    // Aquesta funció la crida Android per crear el ViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        // 1. Comprovem que ens demanen el ViewModel correcte
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {

            // 2. Creem el ViewModel manualment i li "injectem" el repository
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(repository) as T
        }

        // Si ens demanen un altre tipus de viewmodel, donem error
        throw IllegalArgumentException("Classe ViewModel desconeguda")
    }
}