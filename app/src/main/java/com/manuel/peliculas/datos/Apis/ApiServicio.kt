package com.manuel.peliculas.datos.Apis

import android.util.Log
import com.manuel.peliculas.datos.informacion.Lista_peliculas
import com.manuel.peliculas.datos.informacion.MovieDetailsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiServicio @Inject constructor(private val apiClient: ApiCliente) {
    
    suspend fun getMovies(page: Int): Lista_peliculas? {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getMovies(page = page)
            Log.d("tag", "getMovies code: ${response.code()}")
            response.body()
        }
    }

    suspend fun getMovieId(id: Int): MovieDetailsModel? {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getMovieId(id)
            response.body()
        }
    }
}