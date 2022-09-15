package com.example.retromvvm.model.domain


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("blurHash")
    val blurHash: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("color")
    val color: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("downloadCount")
    val downloadCount: Int?,
    @SerializedName("fullImageUrl")
    val fullImageUrl: String?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("imageId")
    val imageId: String?,
    @SerializedName("likes")
    val likes: Int?,
    @SerializedName("r")
    val r: R?,
    @SerializedName("smallImageUrl")
    val smallImageUrl: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("__v")
    val v: Int?,
    @SerializedName("viewCount")
    val viewCount: Int?
)