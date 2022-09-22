package com.khater.retromvvm.model.domain


import com.google.gson.annotations.SerializedName

data class Wallpaper(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("data")
    val `data`: List<com.khater.retromvvm.model.domain.Data>,
    @SerializedName("paggination")
    val pagination: com.khater.retromvvm.model.domain.Pagination?,
    @SerializedName("success")
    val success: Boolean?
)