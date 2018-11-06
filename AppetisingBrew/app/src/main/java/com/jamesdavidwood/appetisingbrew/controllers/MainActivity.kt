package com.jamesdavidwood.appetisingbrew.controllers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.beust.klaxon.Klaxon
import com.jamesdavidwood.appetisingbrew.R
import com.jamesdavidwood.appetisingbrew.adapters.BeerAdapter
import com.jamesdavidwood.appetisingbrew.models.BeerCoverter
import com.jamesdavidwood.appetisingbrew.models.PunkBrewData
import khttp.async
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val beer_url = "https://api.punkapi.com/v2/beers?page=1&per_page=80"
    val beers = arrayListOf<PunkBrewData>()
    val adapter = BeerAdapter(beers, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BeerRecyclerView.adapter = adapter

        BeerRecyclerView.layoutManager = GridLayoutManager(this, 2)

        retrieveBeers()
    }

    fun updateUI() {
        adapter.notifyDataSetChanged()
    }

    fun retrieveBeers() {
        async.get(beer_url, onResponse = {
            val results = Klaxon()
                .converter(BeerCoverter())
                .parseArray<PunkBrewData>(this.text)

            if ( results != null) {
                for ( beer in results) {
                    beers.add(beer)
                }
            }

            runOnUiThread { updateUI() }
        })
    }
}
