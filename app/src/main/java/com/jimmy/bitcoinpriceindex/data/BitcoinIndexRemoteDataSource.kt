package com.jimmy.bitcoinpriceindex.data

import android.arch.lifecycle.MutableLiveData
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.jimmy.bitcoinpriceindex.data.models.Example
import com.jimmy.bitcoinpriceindex.data.models.Time
import com.jimmy.bitcoinpriceindex.services.APIClient
import com.jimmy.bitcoinpriceindex.services.APIInterface
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import java.util.HashMap

class BitcoinIndexRemoteDataSource {

    private val apiInterface: APIInterface = APIClient.getClient().create(APIInterface::class.java)

    var call : Call<Example> = apiInterface.doGetCurrentprice()

    fun getBitcointRemotePrice(onBitcointRemotePriceCallback: OnBitcointRemotePriceCallback) {

        var listen = call.enqueue( object : Callback<Example> {// how to implements an inline interface deceleration
            override fun onFailure(call: Call<Example>?, t: Throwable?) {
                call?.cancel()

                onBitcointRemotePriceCallback.onFail("\n${t?.cause}  \n${t?.message}")
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
    fun onFail(error: String)
}