package com.manuel.peliculas.datos.informacion

import com.manuel.peliculas.datos.coneccion.Permisos_key
import java.text.NumberFormat

data class Detalles_item(
    val backdropPath: String,
    val genders: String,
    val title: String,
    val overview: String,
    val budget: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val runtime: String,
)

fun MovieDetailsModel.toDomain(): Detalles_item {
    val backdropPath = if (backdropPath.isNullOrEmpty()) "" else "${Permisos_key.IMG}${this.backdropPath}"
    val posterPath = "${Permisos_key.IMG}${this.posterPath}"
    val budget = if (this.budget == 0) "- - -" else getFormattedNumber(this.budget)
    val runtime = if (this.runtime == null) "" else "${this.runtime}"

    val genres = mutableListOf<String>()
    for (i in this.genres) genres.add(i.name)
    val genders = genres.joinToString(separator = ", ").plus(".")

    return Detalles_item(backdropPath, genders, title,
        overview, budget, popularity, posterPath, releaseDate, runtime)
}

private fun getFormattedNumber(tip: Int): String {
    return NumberFormat.getCurrencyInstance().format(tip)
}
