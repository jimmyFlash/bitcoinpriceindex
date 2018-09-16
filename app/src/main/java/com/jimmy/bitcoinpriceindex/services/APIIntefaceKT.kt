package com.jimmy.bitcoinpriceindex.services

import com.jimmy.bitcoinpriceindex.data.models.Example
import retrofit2.Call
import retrofit2.http.GET

interface APIIntefaceKT {

    @GET("bpi/currentprice.json")
    fun doGetCurrentprice(): Call<Example>
}