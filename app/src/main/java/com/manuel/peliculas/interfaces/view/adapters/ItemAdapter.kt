package com.manuel.peliculas.interfaces.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manuel.peliculas.R
import com.manuel.peliculas.databinding.ItemListBinding
import com.manuel.peliculas.datos.informacion.Item_peliculas
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ItemAdapter @Inject constructor() : ListAdapter<Item_peliculas, ItemAdapter.ViewHolder>(DiffCallBack) {

    lateinit var onItemClickListener: (Item_peliculas) -> Unit

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemListBinding.bind(view)

        fun bind(movie: Item_peliculas) {
            Picasso.get().load(movie.posterPath)
                .placeholder(R.drawable.loading_animation)
                .into(binding.ivPoster)
            view.setOnClickListener {
                onItemClickListener(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        return holder.bind(item)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Item_peliculas>() {

        override fun areItemsTheSame(oldItem: Item_peliculas, newItem: Item_peliculas): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item_peliculas, newItem: Item_peliculas): Boolean {
            return oldItem == newItem
        }
    }

}