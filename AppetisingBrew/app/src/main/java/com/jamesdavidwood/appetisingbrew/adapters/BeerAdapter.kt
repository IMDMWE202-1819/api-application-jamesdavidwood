package com.jamesdavidwood.appetisingbrew.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jamesdavidwood.appetisingbrew.R
import com.jamesdavidwood.appetisingbrew.models.PunkBrewData
import com.squareup.picasso.Picasso

class BeerAdapter(private val beers: MutableList<PunkBrewData>, val context:Context, val itemClick: (beer:PunkBrewData) -> Unit): RecyclerView.Adapter<BeerAdapter.BeerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerHolder {
        return BeerHolder(LayoutInflater.from(context).inflate(R.layout.beer_card, parent, false))
    }

    override fun getItemCount(): Int {
        return beers.size
    }

    override fun onBindViewHolder(holder: BeerHolder, position: Int) {
        holder.bindBeer(beers[position])
    }

    inner class BeerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val beerImageView = itemView.findViewById<ImageView>(R.id.beer_card_Image)
        private val beerName = itemView.findViewById<TextView>(R.id.beer_card_Name)


        fun bindBeer(beer: PunkBrewData) {
            beerName.text = beer.name
            Picasso.get().load(beer.image_url).into(beerImageView)
            itemView.setOnClickListener{itemClick(beer)}
        }
    }
}