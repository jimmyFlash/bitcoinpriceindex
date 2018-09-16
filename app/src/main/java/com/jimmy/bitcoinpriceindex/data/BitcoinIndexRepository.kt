package com.jimmy.bitcoinpriceindex.data

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.jimmy.bitcoinpriceindex.Constants
import com.jimmy.bitcoinpriceindex.data.models.Example
import com.jimmy.bitcoinpriceindex.managers.NetManager
import com.jimmy.bitcoinpriceindex.utils.LoadStatusTypeDef
import org.json.JSONObject

class BitcoinIndexRepository(val netManager: NetManager) {

     private val  TAG : String = BitcoinIndexRepository::class.java.simpleName

    var remoteSource : BitcoinIndexRemoteDataSource = BitcoinIndexRemoteDataSource()

    var  example : Example? = null

    private lateinit var handlr: OnBitcointDataReady

    fun getRepositories(handlr_ : OnBitcointDataReady){

        handlr = handlr_
         netManager.isConnectedToInternet?.let {
             if(it) {
                 remoteSource.getBitcointRemotePrice(object : OnBitcointRemotePriceCallback{
                     override fun onFail(error : String,@LoadStatusTypeDef.Status errorCode :Int) {
                         Log.e(TAG, error)
                         LoadJson(netManager.applicationContext,
                                 "sample_response", errorCode, handlr).execute()
                     }

                     override fun onDataReady(data: Example?) {
                         Log.e(TAG, data.toString())
                          example = data
                         handlr.onDataReady(example, LoadStatusTypeDef.SUCCESS_LOAD)
                     }
                 })

             }else{
                 Log.e("OFFLINE", "FAILED TO CONNECT ONLINE")

                 LoadJson(netManager.applicationContext,
                         "sample_response",
                         LoadStatusTypeDef.OFFLINE_LOAD,
                         handlr).execute()
             }
         }
    }




    interface OnBitcointDataReady{
        fun onDataReady(data : Example?,@LoadStatusTypeDef.Status code : Int)
    }

    companion object {
        internal class LoadJson(private val ctx: Context,
                                private val indexRepository: String,
                                @LoadStatusTypeDef.Status val code: Int,
                                private val handlr: OnBitcointDataReady) : AsyncTask<String, String, JSONObject>() {

            private var theJson: JSONObject? = null

            override fun doInBackground(vararg params: String): JSONObject? {
                theJson = try {
                    LoadLocalJSON.loadJSONFromAsset(ctx, indexRepository)
                } catch (e: Exception) {
                    null
                }
                return theJson
            }

            override fun onPostExecute(jsonObject: JSONObject?) {

                if(jsonObject != null)  convertToGson(jsonObject, code, handlr)

            }
        }

        fun  convertToGson(jsonObject: JSONObject?, code: Int, handlr: OnBitcointDataReady) {
            val gs = Gson()
            val example =  gs.fromJson<Example>(jsonObject.toString(), Example::class.java)
            handlr.onDataReady(example, code)

        }
    }

}