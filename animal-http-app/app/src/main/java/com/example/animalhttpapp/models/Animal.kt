package com.example.animalhttpapp.models

import com.example.animalhttpapp.JSONConvertable
import com.google.gson.annotations.SerializedName

data class Animal(
    val name: String = "test name",
    @SerializedName("animal_type") val type: String = "test type",
    val habitat: String = "test habitat",
    @SerializedName("geo_range") val geoRange: String = "test geo range"
) : JSONConvertable