package com.example.retromvvm.model.domain


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("next")
    val next: Next?,
    val prev:Next?
)