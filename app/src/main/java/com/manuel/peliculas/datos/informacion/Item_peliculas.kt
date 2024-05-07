package com.manuel.peliculas.datos.informacion

import com.manuel.peliculas.datos.coneccion.Permisos_key

data class Item_peliculas(
    val id: Int,
    val title: String,
    val posterPath: String,
)

fun ResultApi.toDomain(): Item_peliculas {
    val poster = "${Permisos_key.IMG}${this.posterPath}"
    return Item_peliculas(id, title, poster)
}