package com.example.retromvvm.model.networking

import com.example.retromvvm.model.domain.Data
import com.example.retromvvm.model.domain.Wallpaper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    // https://woolapi.herokuapp.com/wallpapers/category?category=Gaming

    @GET("Random")
    suspend fun getHomeFromApi(@Query("page") page: Int?): Wallpaper


    @GET("popular")
    suspend fun getPopularFromApi(@Query("page") page: Int?): Wallpaper

    @GET("popular")
    suspend fun getCategoryDataFromApi(@Query("category") category: String?): Data


    @GET("latest")
    suspend fun getRandomFromApi(@Query("page") page: Int?): Wallpaper

    @GET("category")
    suspend fun getCategoryFromApi(
        @Query("page") page: Int?,
        @Query("category") category: String
    ): Wallpaper
}


