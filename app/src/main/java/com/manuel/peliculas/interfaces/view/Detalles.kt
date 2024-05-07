package com.manuel.peliculas.interfaces.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.manuel.peliculas.R
import com.manuel.peliculas.databinding.FragmentDetailsBinding
import com.manuel.peliculas.interfaces.viewmodel.ApiStatus
import com.manuel.peliculas.interfaces.viewmodel.Detalles
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
@AndroidEntryPoint
class Detalles : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: Detalles by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idMovie = it.getInt("id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        observeStatus()
    }

    private fun observeStatus() {
        viewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                ApiStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.appBar.visibility = View.GONE
                    binding.nestedScrollView.visibility = View.GONE
                    binding.statusOffline.visibility = View.GONE
                }
                ApiStatus.DONE -> {
                    binding.progressBar.visibility = View.GONE
                    binding.appBar.visibility = View.VISIBLE
                    binding.nestedScrollView.visibility = View.VISIBLE
                    binding.statusOffline.visibility = View.GONE
                }
                ApiStatus.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.appBar.visibility = View.GONE
                    binding.nestedScrollView.visibility = View.GONE
                    binding.statusOffline.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun observe() {
        viewModel.movieDetails.observe(viewLifecycleOwner) {

            val img = it.backdropPath.ifEmpty {
                it.posterPath
            }
            Picasso.get().load(img)
                .placeholder(R.drawable.loading_animation)
                .into(binding.image)

            if (it.overview.isEmpty()) {
                binding.tvOverviewEmpty.visibility = View.VISIBLE
                binding.tvDescripcion.visibility = View.GONE
            } else {
                binding.tvOverviewEmpty.visibility = View.GONE
                binding.tvDescripcion.visibility = View.VISIBLE
                binding.tvDescripcion.text = it.overview
            }
            binding.collapsingToolbar.title = it.title
            binding.tvPopularidad.text = it.popularity.toString()
            binding.tvFecha.text = it.releaseDate
            binding.tvPresupuesto.text = it.budget
            binding.tvGenero.text = it.genders
            (if (it.runtime.isEmpty()) {
                getString(R.string.text_empty)
            } else {
                getString(R.string.runtimeLink, it.runtime)
            }).also { st ->
                binding.tvDuracion.text = st }

            Log.d("tag", it.genders)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        var idMovie = 1
    }

}