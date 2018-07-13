package com.jimmy.bitcoinpriceindex.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class USD (
    @SerializedName("code") val code: String,
    @SerializedName("symbol")  val symbol: String,
    @SerializedName("rate") val rate: String,
    @SerializedName("description") val description: String,
    @SerializedName("rate_float") val rateFloat: Double

)