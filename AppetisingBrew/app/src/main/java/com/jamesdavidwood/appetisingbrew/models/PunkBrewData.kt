package com.jamesdavidwood.appetisingbrew.models

import android.os.Parcelable
import com.beust.klaxon.Converter
import com.beust.klaxon.JsonValue
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PunkBrewData(
    val id: Int,
    val name: String,
    val tagline: String,
    val first_brewed: String,
    val description: String,
    val image_url: String,
    val abv: Double?,
    val ibu: Int?,
    val target_fg: Int?,
    val target_og: Int?,
    val ebc: Int?,
    val srm: Int?,
    val ph: Double?,
    val attenuation_level: Int?,
    /*val volume: Volume?,
    val boil_volume: Volume?,
    val method: Method?,
    val ingredients: Ingredients?,(*/
    //val food_pairing: List<String?>?,
    val brewers_tips: String?,
    val contributed_by: String?
) : Parcelable
@Parcelize
data class Method(
    val mash_temp: List<MashTemp?>?,
    val fermentation: Fermentation?,
    val twist: String?
) : Parcelable
@Parcelize
data class MashTemp(
    val temp: Temp?,
    val duration: Int?
) : Parcelable
@Parcelize
data class Temp(
    val value: Int?,
    val unit: String?
) : Parcelable
@Parcelize
data class Fermentation(
    val temp: Temp?
) : Parcelable
@Parcelize
data class Volume(
    val value: Int?,
    val unit: String?
) : Parcelable
@Parcelize
data class Ingredients(
    val malt: List<Malt?>?,
    val hops: List<Hop?>?,
    val yeast: String?
) : Parcelable

@Parcelize
data class Malt(
    val name: String?,
    val amount: Amount?
) : Parcelable
@Parcelize
data class Amount(
    val value: Double?,
    val unit: String?
) : Parcelable
@Parcelize
data class Hop(
    val name: String?,
    val amount: Amount?,
    val add: String?,
    val attribute: String?
) : Parcelable

class BeerCoverter : Converter {
    override fun canConvert(cls: Class<*>): Boolean {
        return cls == PunkBrewData::class.java
    }

    override fun fromJson(jv: JsonValue): Any {
        val beer = jv.obj

        if ( beer != null) {

            return PunkBrewData(
                beer["id"].toString().toInt(),
                beer["name"].toString(),
                beer["tagline"].toString(),
                beer["first_brewed"].toString(),
                beer["description"].toString(),
                beer["image_url"].toString(),
                beer["abv"].toString().toDoubleOrNull(),
                beer["ibu"].toString().toIntOrNull(),
                beer["target_fg"].toString().toIntOrNull(),
                beer["target_og"].toString().toIntOrNull(),
                beer["ebc"].toString().toIntOrNull(),
                beer["srm"].toString().toIntOrNull(),
                beer["ph"].toString().toDoubleOrNull(),
                beer["attenuation_level"].toString().toIntOrNull(),
                //beer["food_pairing"].toString(),
                beer["brewers_tips"].toString(),
                beer["contributed_by"].toString()


            )
        }

        return "invalid"
    }

    override fun toJson(value: Any): String {
        return "{}"
    }

}

