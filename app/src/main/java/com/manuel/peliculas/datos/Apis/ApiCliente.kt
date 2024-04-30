package com.manuel.peliculas.datos.Apis

import com.manuel.peliculas.datos.coneccion.Permisos_key
import com.manuel.peliculas.datos.informacion.Lista_peliculas
import com.manuel.peliculas.datos.informacion.MovieDetailsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCliente {
    
    @GET(value = "3/movie/popular?")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = Permisos_key.API_KEY,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): Response<Lista_peliculas>
    
    @GET("3/movie/{movieId}?")
    suspend fun getMovieId(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = Permisos_key.API_KEY,
        @Query("language") language: String = "es-ES",
    ): Response<MovieDetailsModel>
}