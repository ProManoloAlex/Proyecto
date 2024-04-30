package com.manuel.peliculas.interfaces.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manuel.peliculas.R
import com.manuel.peliculas.datos.Apis.Seleccionar_lista
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Seleccionar_lista {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSelected(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)

        val detailsFragment = Detalles()
        detailsFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, detailsFragment)
            .addToBackStack(null)
            .commit()
    }

}