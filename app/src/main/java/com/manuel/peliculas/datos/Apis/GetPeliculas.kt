package com.manuel.peliculas.datos.Apis

import com.manuel.peliculas.datos.MovieRepository
import com.manuel.peliculas.datos.informacion.Item_peliculas
import javax.inject.Inject

class GetPeliculas @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun getAll(page: Int): MutableList<Item_peliculas> {
        return movieRepository.getMovies(page)
    }
}