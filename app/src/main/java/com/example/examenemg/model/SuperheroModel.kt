package com.example.examenemg.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SuperheroModel(
    val response: String,
    val id: String,
    val name: String,
    val powerstats: Powerstats,
    val biography: Biography,
    val appearance: Appearance,
    val work: Work,
    val connections: Connections,
    val image: Photo
) : Serializable
{
    class Powerstats(
        val intelligence: String,
        val strength: String,
        val speed: String,
        val durability: String,
        val power: String,
        val combat: String
    ): Serializable

    class Biography(
        @SerializedName("full-name") val full_name: String,
        @SerializedName("alter-egos") val alter_egos: String,
        val aliases: List<String>,
        @SerializedName("place-of-birth") val place_of_birth: String,
        @SerializedName("first-appearance") val first_appearance: String,
        val publisher: String,
        val alignment: String
    ): Serializable

    class Appearance(
        val gender: String,
        val race: String,
        val height: List<String>,
        val weight: List<String>,
        @SerializedName("eye-color") val eye_color: String,
        @SerializedName("hair-color") val hair_color: String
    ): Serializable

    class Work(
        val occupation: String,
        val base: String
    ): Serializable

    class Connections(
        @SerializedName("group-affiliation") val group_affiliation : String,
        val relatives: String
    ): Serializable

    class Photo(
        val url: String
    ): Serializable
}

