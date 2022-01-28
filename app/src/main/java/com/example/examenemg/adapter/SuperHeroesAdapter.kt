package com.example.examenemg.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examenemg.R
import com.example.examenemg.databinding.ItemSuperheroBinding
import com.example.examenemg.interfaces.ItemClickListener
import com.example.examenemg.model.SuperheroModel
import com.squareup.picasso.Picasso

class SuperHeroesAdapter(private val superheros: List<SuperheroModel>, private val itemClickListener: ItemClickListener): RecyclerView.Adapter<SuperHeroesAdapter.SuperherosHolder>() {

    class SuperherosHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding = ItemSuperheroBinding.bind(view)
        fun render(superheroModel: SuperheroModel){
            binding.tvnameSuperhero.text = superheroModel.name
            Picasso.get().load(superheroModel.image.url).into(binding.ivSuperhero)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperherosHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperherosHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(holder: SuperherosHolder, position: Int) {
        val superhero = superheros[position]
        holder.render(superhero)
        holder.itemView.setOnClickListener{
            itemClickListener.onItemClickListener(superhero)
        }
    }

    override fun getItemCount(): Int = superheros.size

}