package com.manuel.peliculas.datos.Apis

import com.manuel.peliculas.datos.MovieRepository
import com.manuel.peliculas.datos.informacion.Detalles_item
import javax.inject.Inject

class GetDetalles @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun getDetails(id: Int): Detalles_item? {
        return movieRepository.getDetailsMovie(id)
    }
}