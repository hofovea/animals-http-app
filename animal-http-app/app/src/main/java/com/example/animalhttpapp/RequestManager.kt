package com.example.animalhttpapp

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.animalhttpapp.models.Animal
import com.example.animalhttpapp.models.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL

class RequestManager {
    private var client = OkHttpClient()
    private val gson = Gson()
    private lateinit var onAnimalListReceived: (animalsList: List<Animal>) -> Unit
    private val animalsApi = RetrofitClient.getInstance().create(AnimalsAPI::class.java)
    private val mainHandler = Handler(Looper.getMainLooper())

    fun setonAnimalListReceivedHandler(handler: (animalsList: List<Animal>) -> Unit) {
        onAnimalListReceived = handler
    }

    fun start() {
        Thread {
            val result: MutableList<Animal> = ArrayList()
            requestViaOkHttp()?.let { result.addAll(it) }
            requestViaRetrofit()?.let { result.addAll(it) }
            mainHandler.post {
                onAnimalListReceived(result)
            }
        }.start()
    }

    private fun requestViaOkHttp(): List<Animal>? {
        var result: String? = null
        try {
            val request = Request.Builder().url(Constants.fullUrl).build()
            val response = client.newCall(request).execute()
            result = response.body?.string()
        } catch (e: Exception) {
            Log.d("Exception", "requestViaOkHttp: ${e.message}")
        }
        Log.d("TAGGG", "onViewCreated: $result")
        if (result != null) {
            val token = object : TypeToken<List<Animal>>() {}.type
            val animals = gson.fromJson<List<Animal>>(result, token)
            Log.d("TAGGG", "requestViaOkHttp: $animals")
            return animals
        }
        return null
    }

    private fun requestViaRetrofit(): List<Animal>? {
        val result = animalsApi.getAnimals("3").execute()
        Log.d("TAGGGG", "requestViaRetrofit: ${result.body()}")
        return result.body()
    }
}