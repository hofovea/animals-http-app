package com.example.animalhttpapp

import com.example.animalhttpapp.models.Animal
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimalsAPI {
    @GET("/animals/rand/{amount}")
    fun getAnimals(@Path("amount") amount: String): Call<List<Animal>>
}