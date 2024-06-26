package com.manuel.peliculas.datos.informacion

import com.google.gson.annotations.SerializedName

data class Lista_peliculas(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: MutableList<ResultApi>,
    @SerializedName("total_pages") val totalPages: Int
)

data class ResultApi(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String
)
