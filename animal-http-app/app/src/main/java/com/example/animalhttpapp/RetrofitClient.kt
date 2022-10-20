package com.example.animalhttpapp

import com.example.animalhttpapp.models.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val mOkHttpClientBuilder = OkHttpClient.Builder()

    fun getInstance(): Retrofit {
        if (BuildConfig.DEBUG) {
            val mHttpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            mOkHttpClientBuilder.addInterceptor(mHttpLoggingInterceptor)
        }

        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClientBuilder.build())
            .build()
    }

}