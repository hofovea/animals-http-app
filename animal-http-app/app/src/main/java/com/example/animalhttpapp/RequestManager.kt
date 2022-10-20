package com.example.animalhttpapp

import android.os.AsyncTask
import android.util.Log
import com.example.animalhttpapp.models.Animal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL

class RequestManager: AsyncTask<Unit, Unit, List<Animal>>() {
    private var client = OkHttpClient()
    private val urlString = "https://zoo-animal-api.herokuapp.com/animals/rand/5"
    private val gson = Gson()
    private lateinit var onAnimalListReceived: (animalsList: List<Animal>) -> Unit
    private val animalsApi = RetrofitClient.getInstance().create(AnimalsAPI::class.java)

    fun setonAnimalListReceivedHandler(handler: (animalsList: List<Animal>) -> Unit) {
        onAnimalListReceived = handler
    }

    private fun requestViaOkHttp(): List<Animal>? {
        var result: String? = null
        try {
            val request = Request.Builder().url(urlString).build()
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

    override fun doInBackground(vararg params: Unit?): List<Animal> {
        val result: MutableList<Animal> = ArrayList()
        requestViaOkHttp()?.let { result.addAll(it) }
        requestViaRetrofit()?.let { result.addAll(it) }
        return result

    }

    override fun onPostExecute(result: List<Animal>) {
        onAnimalListReceived(result)
    }
}