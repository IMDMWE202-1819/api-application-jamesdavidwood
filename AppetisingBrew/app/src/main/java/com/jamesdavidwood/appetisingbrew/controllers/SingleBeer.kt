package com.jamesdavidwood.appetisingbrew.controllers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jamesdavidwood.appetisingbrew.R
import com.jamesdavidwood.appetisingbrew.models.PunkBrewData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_single_beer.*

class SingleBeer : AppCompatActivity() {

    lateinit var beer: PunkBrewData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_beer)

        beer = intent.getParcelableExtra(EXTRA_BEER)

        Picasso.get().load(beer.image_url).into(SingleBeerImage)
        SingleBeerName.text = beer.name
        LongBeerDesc.text = beer.description
        FirstBrewed.text = "first brewed: ${beer.first_brewed}"
        BeerABV.text = "ABV: ${beer.abv.toString()}%"
    }
}
