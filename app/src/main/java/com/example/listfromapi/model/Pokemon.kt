package com.example.listfromapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon (
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("height") val height: Int,
    @SerialName("weight") val weight: Int,
    @SerialName("sprites") val pokemonImages: PokemonImages,
    @SerialName("stats") val stats: List<Stats>
)

//Pokemon Stats API DON'T RETURN IT
@Serializable
data class Stats(
    @SerialName("base_stat") val baseStat: Int,
    @SerialName("effort") val effort: Int,
    @SerialName("stat") val statType: Stat
)
@Serializable
data class Stat(
    @SerialName("name") val name: String
)
// End Pokemon Stats

// Pokemon Images API DON'T RETURN IT
@Serializable
data class PokemonImages(
    @SerialName("front_default") val front: String,
    @SerialName("front_shiny") val frontS: String,
    @SerialName("back_default") val back: String,
    @SerialName("back_shiny") val backS: String,
    @SerialName("official_artwork") val otherImg: OtherImages
)
@Serializable
data class OtherImages(
    @SerialName("official_artwork") val artwork: OfficialArtwork
)
@Serializable
data class OfficialArtwork(
    @SerialName("front_default") val front: String,
    @SerialName("front_shiny") val frontS: String
)