package com.example.listfromapi.ui.theme

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


enum class ColorPalette{
    Base, Ocaso, Master
}
object AppColors{
    val PokedexBack = mutableStateOf(Color(0xFFC7FDFD))
    val PokedexData = mutableStateOf(Color(0xFF3197D5))
    val PokedexBorder = mutableStateOf(Color(0xFFD0D1D1))
    val PokedexButtonBack = mutableStateOf(Color(0xFFD60A2A))
    val PokedexButton = mutableStateOf(Color(0xFF5B8B46))

    fun changeAppColors(colorPalette: ColorPalette){
        when (colorPalette) {
            ColorPalette.Base -> basePalette()
            ColorPalette.Ocaso -> ocasoPalette()
            ColorPalette.Master -> masterPalette()
            else -> {}
        }
    }

    private fun basePalette(){
        PokedexBack.value = Color(0xFFC7FDFD)
        PokedexData.value = Color(0xFF3197D5)
        PokedexBorder.value = Color(0xFFD0D1D1)
        PokedexButtonBack.value = Color(0xFFD60A2A)
        PokedexButton.value = Color(0xFF5B8B46)
    }
    private fun ocasoPalette(){
        PokedexBack.value = Color(0xFF92C48A)
        PokedexData.value = Color(0xFF6BB259)
        PokedexBorder.value = Color(0xFF2E402E)
        PokedexButtonBack.value = Color(0xFF000000)
        PokedexButton.value = Color(0xFFC96B2E)
    }
    private fun masterPalette(){
        PokedexBack.value = Color(0xFF92C48A)
        PokedexData.value = Color(0xFF6BB259)
        PokedexBorder.value = Color(0xFFF2F4E9)
        PokedexButtonBack.value = Color(0xFF743FC9)
        PokedexButton.value = Color(0xFFE95AB2)
    }
}
