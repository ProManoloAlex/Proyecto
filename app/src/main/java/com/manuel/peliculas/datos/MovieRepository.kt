package com.manuel.peliculas.datos

import com.manuel.peliculas.datos.coneccion.Permisos_key
import com.manuel.peliculas.datos.informacion.Lista_peliculas
import com.manuel.peliculas.datos.Apis.ApiServicio
import com.manuel.peliculas.datos.informacion.Detalles_item
import com.manuel.peliculas.datos.informacion.Item_peliculas
import com.manuel.peliculas.datos.informacion.toDomain
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiServicio
) {

    suspend fun getMovies(page: Int): MutableList<Item_peliculas> {
        val response = apiService.getMovies(page)
        Permisos_key.pagesTotal = response?.pTotal() ?: 1
        return response?.results?.map { it.toDomain() } as MutableList<Item_peliculas>
    }

    private fun Lista_peliculas.pTotal() = totalPages

    suspend fun getDetailsMovie(id: Int): Detalles_item? {
        val response = apiService.getMovieId(id)
        return response?.toDomain()
    }
}