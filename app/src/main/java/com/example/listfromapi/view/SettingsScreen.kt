package com.example.listfromapi.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.listfromapi.ui.theme.AppColors
import com.example.listfromapi.ui.theme.ColorPalette

@Composable
fun SettingsScreen(){
    var selectedColors: String by remember { mutableStateOf("") }
    var expanded: Boolean by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(AppColors.PokedexButtonBack.value)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize(0.9f)
                .clip(
                    CutCornerShape(
                        topEnd = 20.dp,
                        topStart = 2.dp,
                        bottomEnd = 2.dp,
                        bottomStart = 20.dp
                    )
                )
                .background(AppColors.PokedexBorder.value)
                .align(Alignment.Center)
        ) {
            val (currentColorPalette, selectColorPalette) = createRefs()
            OutlinedTextField(
                value = selectedColors,
                onValueChange = { selectedColors = it },
                enabled = false,
                readOnly = true,
                modifier = Modifier
                    .clickable { expanded = true }
                    .fillMaxWidth(0.8f)
                    .background(AppColors.PokedexData.value)
                    .constrainAs(currentColorPalette){
                        top.linkTo(parent.top, margin = 10.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColors.PokedexBack.value)
                    .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                    .constrainAs(selectColorPalette){
                        top.linkTo(currentColorPalette.bottom)
                        start.linkTo(currentColorPalette.start)
                        end.linkTo(currentColorPalette.end)
                    }
            ){
                ColorPalette.entries.forEach { colorP ->
                    DropdownMenuItem(
                        text = { Text(text = colorP.name) },
                        onClick = {
                            expanded = false
                            selectedColors = colorP.name + " Palette"
                        })
                }
            }
        }
    }
}