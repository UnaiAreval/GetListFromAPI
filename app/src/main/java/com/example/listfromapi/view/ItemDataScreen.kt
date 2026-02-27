package com.example.listfromapi.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.listfromapi.R
import com.example.listfromapi.model.PokemonImages
import com.example.listfromapi.ui.theme.PokedexBack
import com.example.listfromapi.ui.theme.PokedexBorder
import com.example.listfromapi.ui.theme.PokedexButtonBack
import com.example.listfromapi.ui.theme.PokedexData
import com.example.listfromapi.viewModel.PokemonViewModel

@Composable
fun ItemDataScreen(navController: NavController, pokemonVM: PokemonViewModel){

    Box( modifier = Modifier
        .fillMaxSize()
        .background(PokedexButtonBack) ){
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize(0.8f)
                .clip(
                    CutCornerShape(
                        topEnd = 20.dp,
                        topStart = 5.dp,
                        bottomEnd = 5.dp,
                        bottomStart = 20.dp
                    )
                )
                .background(PokedexBack)
                .border(
                    10.dp,
                    PokedexBorder,
                    CutCornerShape(
                        topEnd = 20.dp,
                        topStart = 5.dp,
                        bottomEnd = 5.dp,
                        bottomStart = 20.dp
                    )
                )
                .align(Alignment.Center)
        ) {
            val (image, imageChanger, nameAndId, stats, addToFavorite) = createRefs()

            AsyncImage(
                model = pokemonVM.currentImage.value,
                contentDescription = "PokemonImage",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(PokedexData)
                    .padding(5.dp)
                    .border(5.dp, PokedexBack, CircleShape)
                    .constrainAs(image) {
                        top.linkTo(parent.top, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            var show by remember { mutableStateOf(false) }
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(PokedexData)
                    .padding(2.dp)
                    .clickable { show = true }
                    .constrainAs(imageChanger) {
                        bottom.linkTo(image.bottom)
                        start.linkTo(image.end)
                    }
            ){
                Icon(
                    painter = painterResource(R.drawable.dropdown_menu),
                    contentDescription ="Image Selector Displayer",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(10.dp)
                        .align(Alignment.Center)
                )
            }
            Box(
                modifier = Modifier
                    .height(45.dp)
                    .constrainAs(nameAndId) {
                        top.linkTo(image.bottom, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ){
                Text("Nº ${pokemonVM.pokemon.value?.id}", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.align(Alignment.TopCenter))
                Text("${pokemonVM.pokemon.value?.name}", fontSize = 15.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.align(Alignment.BottomCenter))
            }

            LazyColumn(
                modifier = Modifier
                    .width(250.dp)
                    .background(PokedexData)
                    .clip(RoundedCornerShape(20.dp))
                    .constrainAs(stats){
                        top.linkTo(nameAndId.bottom, margin = 10.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                items(pokemonVM.pokemon.value!!.stats){ stat ->
                    Box(modifier = Modifier.height(50.dp)){
                        Text("${stat.stat.name}: ", fontSize = 15.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.align(Alignment.TopStart).padding(5.dp))
                        Text("Base stat: ${stat.base_stat}  |  Effort: ${stat.effort}", fontSize = 10.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.align(Alignment.BottomCenter).padding(5.dp))
                    }
                    Spacer(Modifier.height(1.dp).width(225.dp).background(Color.Black))
                }
            }
            if (show) ImageSelectorDialog(pokemonVM, show, { show = false })
        }
    }
}

@Composable
fun ImageSelectorDialog(pVM: PokemonViewModel, show: Boolean, onDismiss: () -> Unit){
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ){

        ConstraintLayout(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(PokedexData)
                .padding(5.dp)
                .border(2.dp, PokedexBack, RoundedCornerShape(20.dp))
        ) {
            val (
                title,
                frontBase,
                frontShiny,
                backBase,
                backShiny,
                otherBase,
                otherShiny
            ) = createRefs()

            Text(
                text = "Image Selector",
                modifier = Modifier.constrainAs(title){
                    top.linkTo(parent.top, margin = 5.dp)
                    start.linkTo(parent.start, margin = 5.dp)
                    end.linkTo(parent.end, margin = 5.dp)
                }
            )

            AsyncImage(
                model = pVM.pokemon.value?.sprites?.front_default,
                contentDescription = "ImageFD",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(PokedexData)
                    .padding(5.dp)
                    .border(2.dp, PokedexBack, CircleShape)
                    .constrainAs(frontBase) {
                        top.linkTo(title.bottom, margin = 5.dp)
                        start.linkTo(title.start)
                        end.linkTo(frontBase.start, margin = 5.dp)
                    }
                    .clickable{
                        pVM.currentImage.value = pVM.pokemon.value?.sprites?.front_default!!
                        onDismiss()
                    }
            )
            AsyncImage(
                model = pVM.pokemon.value?.sprites?.front_shiny,
                contentDescription = "ImageFS",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(PokedexData)
                    .padding(5.dp)
                    .border(2.dp, PokedexBack, CircleShape)
                    .constrainAs(frontShiny) {
                        top.linkTo(title.bottom, margin = 5.dp)
                        start.linkTo(frontBase.end, margin = 5.dp)
                        end.linkTo(title.end)
                    }
                    .clickable{
                        pVM.currentImage.value = pVM.pokemon.value?.sprites?.front_shiny!!
                        onDismiss()
                    }
            )
            AsyncImage(
                model = pVM.pokemon.value?.sprites?.back_default,
                contentDescription = "ImageBD",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(PokedexData)
                    .padding(5.dp)
                    .border(2.dp, PokedexBack, CircleShape)
                    .constrainAs(backBase) {
                        top.linkTo(frontBase.bottom, margin = 5.dp)
                        start.linkTo(title.start)
                        end.linkTo(backShiny.start, margin = 5.dp)
                    }
                    .clickable{
                        pVM.currentImage.value = pVM.pokemon.value?.sprites?.back_default!!
                        onDismiss()
                    }
            )
            AsyncImage(
                model = pVM.pokemon.value?.sprites?.back_shiny,
                contentDescription = "ImageBS",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(PokedexData)
                    .padding(5.dp)
                    .border(2.dp, PokedexBack, CircleShape)
                    .constrainAs(backShiny) {
                        top.linkTo(frontShiny.bottom, margin = 5.dp)
                        start.linkTo(backBase.end, margin = 5.dp)
                        end.linkTo(title.end)
                    }
                    .clickable{
                        pVM.currentImage.value = pVM.pokemon.value?.sprites?.back_shiny!!
                        onDismiss()
                    }
            )
            if (pVM.pokemon.value?.sprites?.other?.official_artwork != null){
                AsyncImage(
                    model = pVM.pokemon.value?.sprites?.other?.official_artwork?.front_default!!,
                    contentDescription = "ImageBS",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(PokedexData)
                        .padding(5.dp)
                        .border(2.dp, PokedexBack, CircleShape)
                        .constrainAs(otherBase) {
                            top.linkTo(backBase.bottom, margin = 5.dp)
                            start.linkTo(title.start)
                            end.linkTo(otherShiny.start, margin = 5.dp)
                        }
                        .clickable{
                            pVM.currentImage.value = pVM.pokemon.value?.sprites?.other?.official_artwork?.front_default!!
                            onDismiss()
                        }
                )
                AsyncImage(
                    model = pVM.pokemon.value?.sprites?.other?.official_artwork?.front_shiny!!,
                    contentDescription = "ImageBS",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(PokedexData)
                        .padding(5.dp)
                        .border(2.dp, PokedexBack, CircleShape)
                        .constrainAs(otherShiny) {
                            top.linkTo(backShiny.bottom, margin = 5.dp)
                            end.linkTo(title.end)
                            start.linkTo(otherBase.end, margin = 5.dp)
                        }
                        .clickable{
                            pVM.currentImage.value = pVM.pokemon.value?.sprites?.other?.official_artwork?.front_shiny!!
                            onDismiss()
                        }
                )
            }
        }
    }
}