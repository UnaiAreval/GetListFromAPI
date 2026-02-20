package com.example.listfromapi.data

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.listfromapi.ui.theme.ColorPalette

class SettingsRepo(context: Context) {
    // Creem o obrim el fitxer XML anomenat "AppSettings"
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

    // Funció per guardar el nom
    @SuppressLint("CommitPrefEdits") //I don't know, the IDE asked me to write this
    fun <T> safeLocalData(key: String, value: T) {
        with(sharedPreferences.edit()){
            when (value){
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
            }
        }
    }

    // Funció per llegir el nom (retorna buit si no existeix)
    fun obtenirNom(): String {
        return sharedPreferences.getString("nom_usuari", "") ?: ""
    }
    fun getColorPalette(): ColorPalette{
        when (sharedPreferences.getString("color_palette", "")){
            "Base" -> return ColorPalette.Base
            "Ocaso" -> return ColorPalette.Ocaso
            "Master" -> return ColorPalette.Base
            else -> return ColorPalette.Base
        }
    }
}