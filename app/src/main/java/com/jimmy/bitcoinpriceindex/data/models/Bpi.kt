package com.jimmy.bitcoinpriceindex.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class Bpi (
    @SerializedName("USD")  val uSD: USD,
    @SerializedName("GBP")  val gBP: GBP,
    @SerializedName("EUR") val eUR: EUR
)