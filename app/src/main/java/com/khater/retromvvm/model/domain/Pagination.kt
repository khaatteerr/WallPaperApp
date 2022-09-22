package com.khater.retromvvm.model.domain


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("next")
    val next: com.khater.retromvvm.model.domain.Next?,
    val prev: com.khater.retromvvm.model.domain.Next?
)