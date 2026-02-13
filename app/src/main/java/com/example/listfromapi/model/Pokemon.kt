package com.example.listfromapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class Pokemon (
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: PokemonImages,
    val stats: List<Stats>
)

//Pokemon Stats API DON'T RETURN IT

data class Stats(
    val base_stat: Int,
    val effort: Int,
    val stat: Stat
)

data class Stat(
    val name: String
)
// End Pokemon Stats

// Pokemon Images API DON'T RETURN IT

data class PokemonImages(
    val front_default: String,
    val front_shiny: String,
    val back_default: String,
    val back_shiny: String,
    val other: OtherImages
)

data class OtherImages(
    val official_artwork: OfficialArtwork
)

data class OfficialArtwork(
    val front_default: String,
    val front_shiny: String
)