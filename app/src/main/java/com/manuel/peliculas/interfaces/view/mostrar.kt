package com.manuel.peliculas.interfaces.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class mostrar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}