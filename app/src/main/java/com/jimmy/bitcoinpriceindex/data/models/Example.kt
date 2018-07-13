package com.jimmy.bitcoinpriceindex.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class Example (
    @SerializedName("time")  val time: Time,
    @SerializedName("disclaimer")  val disclaimer: String,
    @SerializedName("chartName")  val chartName: String,
    @SerializedName("bpi") val bpi: Bpi
)