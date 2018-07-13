package com.jimmy.bitcoinpriceindex.services;

import com.jimmy.bitcoinpriceindex.data.models.Example;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jamal.safwat on 1/7/2018.
 */

public interface APIInterface {

    @GET("bpi/currentprice.json")
    Call<Example> doGetCurrentprice();

}
