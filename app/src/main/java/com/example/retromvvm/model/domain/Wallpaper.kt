package com.example.retromvvm.model.domain


import com.google.gson.annotations.SerializedName

data class Wallpaper(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("paggination")
    val pagination: Pagination?,
    @SerializedName("success")
    val success: Boolean?
)