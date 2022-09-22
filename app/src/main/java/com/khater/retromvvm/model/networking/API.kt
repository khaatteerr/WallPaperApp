package com.khater.retromvvm.model.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {
    private const val BASE_URL = "https://woolapi.herokuapp.com/wallpapers/"
     private val retrofit = Retrofit.Builder()
        .baseUrl(com.khater.retromvvm.model.networking.API.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: com.khater.retromvvm.model.networking.RetroService = com.khater.retromvvm.model.networking.API.retrofit.create(
        com.khater.retromvvm.model.networking.RetroService::class.java)

}