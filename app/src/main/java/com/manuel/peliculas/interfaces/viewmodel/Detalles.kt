package com.manuel.peliculas.interfaces.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuel.peliculas.datos.Apis.GetDetalles
import com.manuel.peliculas.datos.informacion.Detalles_item
import com.manuel.peliculas.interfaces.view.Detalles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Detalles @Inject constructor(
    private val getDetails: GetDetalles
) : ViewModel() {

    private var _movieDetails = MutableLiveData<Detalles_item>()
    val movieDetails: LiveData<Detalles_item> get() = _movieDetails

    private var _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> get() = _status

    init {
        getAllDetails(Detalles.idMovie)
    }

    private fun getAllDetails(id: Int) {
        _status.value = ApiStatus.LOADING
        viewModelScope.launch {
            try {
                _movieDetails.value = getDetails.getDetails(id)
                _status.value = ApiStatus.DONE
                Log.d("tag", "${movieDetails.value}")
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                Log.d("tag", "${e.message}")
            }
        }
    }
}