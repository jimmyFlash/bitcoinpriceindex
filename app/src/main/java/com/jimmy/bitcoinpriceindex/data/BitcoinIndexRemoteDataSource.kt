package com.jimmy.bitcoinpriceindex.data

import com.jimmy.bitcoinpriceindex.Constants.FAIL_TO_LOAD_ERROR
import com.jimmy.bitcoinpriceindex.data.models.Example
import com.jimmy.bitcoinpriceindex.services.APIClient
import com.jimmy.bitcoinpriceindex.services.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BitcoinIndexRemoteDataSource {

    private val apiInterface: APIInterface = APIClient.getClient().create(APIInterface::class.java)

    var call : Call<Example> = apiInterface.doGetCurrentprice()

    fun getBitcointRemotePrice(onBitcointRemotePriceCallback: OnBitcointRemotePriceCallback) {

        call.clone().enqueue( object : Callback<Example> {// how to implements an inline interface deceleration
            override fun onFailure(call: Call<Example>?, t: Throwable?) {
                call?.cancel()
                onBitcointRemotePriceCallback.onFail("\n${t?.cause}  \n${t?.message}", FAIL_TO_LOAD_ERROR)
            }

            override fun onResponse(call: Call<Example>?, response: Response<Example>?) {
                val bitcoinPriceIndexData = response?.body()
                if (bitcoinPriceIndexData != null) {
                    onBitcointRemotePriceCallback.onDataReady(bitcoinPriceIndexData)
                }
            }
        })
    }
}

interface OnBitcointRemotePriceCallback {
    fun onDataReady(data : Example?)
    fun onFail(error: String, errorCode : Int )
}