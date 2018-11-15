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
        FirstBrewed.text = "First brewed: ${beer.first_brewed}"
        BeerABV.text = "ABV: ${beer.abv.toString()}%"
        Tagline.text = beer.tagline
        IBU.text = "International Bitterness Unit: ${beer.ibu.toString()}"
        TargetFG.text = "Target FG: ${beer.target_fg.toString()}"
        TargetOG.text = "Target OG: ${beer.target_og.toString()}"
        EBC.text = "EBC: ${beer.ebc.toString()}"
        SRM.text = "SRM: ${beer.srm.toString()}"
        PH.text = "PH: ${beer.ph.toString()}"
        AttenuationLevel.text = "Attenuation Level: ${beer.attenuation_level.toString()}"
        BrewersTips.text = "Brewers Tips: ${beer.brewers_tips}"
        ContributedBy.text = "Contributed by: ${beer.contributed_by}"


    }
}
