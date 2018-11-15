package com.jamesdavidwood.appetisingbrew.controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.beust.klaxon.Klaxon
import com.jamesdavidwood.appetisingbrew.R
import com.jamesdavidwood.appetisingbrew.adapters.BeerAdapter
import com.jamesdavidwood.appetisingbrew.models.BeerCoverter
import com.jamesdavidwood.appetisingbrew.models.PunkBrewData
import khttp.async
import kotlinx.android.synthetic.main.activity_main.*

const val EXTRA_BEER = "beer"

class MainActivity : AppCompatActivity() {
    private var page = 1
    private var beerUrl = "https://api.punkapi.com/v2/beers?page=$page&per_page=80"
    private val beers = arrayListOf<PunkBrewData>()
    private val adapter = BeerAdapter(beers, this) {beer ->
        val intent = Intent(this, SingleBeer::class.java)
        intent.putExtra(EXTRA_BEER, beer)

        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BeerRecyclerView.adapter = adapter

        BeerRecyclerView.layoutManager = GridLayoutManager(this, 2)

        retrieveBeers()
    }

    private fun updateUI() {
        adapter.notifyDataSetChanged()
    }

    private fun retrieveBeers() {
        println(beerUrl)
        async.get(beerUrl, onResponse = {
            val results = Klaxon()
                .converter(BeerCoverter())
                .parseArray<PunkBrewData>(this.text)

            if ( results != null) {
                for ( beer in results) {
                    beers.add(beer)
                }
                if (results.size > 0) {
                    page += 1
                    beerUrl = "https://api.punkapi.com/v2/beers?page=$page&per_page=80"
                    retrieveBeers()
                }
            }

            runOnUiThread { updateUI() }
        })
    }
}
