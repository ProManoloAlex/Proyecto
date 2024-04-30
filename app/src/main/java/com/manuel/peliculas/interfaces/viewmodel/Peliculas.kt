package com.manuel.peliculas.interfaces.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuel.peliculas.datos.Apis.GetPeliculas
import com.manuel.peliculas.datos.informacion.Item_peliculas
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ApiStatus {LOADING, ERROR, DONE}

@HiltViewModel
class MovieViewModel @Inject constructor(private val getMovies: GetPeliculas) : ViewModel() {

    private var _moviesList = MutableLiveData<MutableList<Item_peliculas>>()
    val moviesList: LiveData<MutableList<Item_peliculas>>
        get() = _moviesList

    var page = 1

    private var _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    init {
        getAllMovies(page)
    }

    fun getAllMovies(page: Int) {
        _status.value = ApiStatus.LOADING
        viewModelScope.launch {
            try {
                if (page == 1) {
                    _moviesList.value = getMovies.getAll(page)
                } else {
                    _moviesList.value?.addAll(getMovies.getAll(page))
                }
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _status.value = ApiStatus.ERROR
            }
        }

    }
}