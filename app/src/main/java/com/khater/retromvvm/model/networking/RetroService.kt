package com.khater.retromvvm.model.networking

import com.khater.retromvvm.model.domain.Wallpaper
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    // https://woolapi.herokuapp.com/wallpapers/category?category=Gaming

    // https://woolapi.herokuapp.com/wallpapers/search?keyword=try

    @GET("Random")
    suspend fun getHomeFromApi(@Query("page") page: Int?): com.khater.retromvvm.model.domain.Wallpaper


    @GET("popular")
    suspend fun getPopularFromApi(@Query("page") page: Int?): com.khater.retromvvm.model.domain.Wallpaper

    @GET("latest")
    suspend fun getRandomFromApi(@Query("page") page: Int?): com.khater.retromvvm.model.domain.Wallpaper

    @GET("category")
    suspend fun getCategoryFromApi(
        @Query("page") page: Int?,
        @Query("category") category: String
    ): com.khater.retromvvm.model.domain.Wallpaper

    @GET("search")
    suspend fun searchFromApi(
        @Query("page") page: Int?,
        @Query("keyword") keyword:  String
    ): com.khater.retromvvm.model.domain.Wallpaper
}


