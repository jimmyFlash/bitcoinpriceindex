package com.jimmy.bitcoinpriceindex.data

import android.arch.lifecycle.MutableLiveData
import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.jimmy.bitcoinpriceindex.data.models.Example
import com.jimmy.bitcoinpriceindex.managers.NetManager
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
                     override fun onFail(str : String) {
                         Log.e(TAG, str)
                         loadJson("sample_response").execute()
                     }

                     override fun onDataReady(data: Example?) {

                         Log.e(TAG, data.toString())
                          example = data

                         handlr.onDataReady(example)
                     }
                 })

             }else{
                 Log.e("OFFLINE", "FAILED TO CONNECT ONLINE")

                 loadJson("sample_response").execute()
             }
         }
    }


    internal inner class loadJson(private val fileJsonNm: String) : AsyncTask<String, String, JSONObject>() {

        private var theJson: JSONObject? = null

        override fun doInBackground(vararg params: String): JSONObject? {


            try {
                theJson = LoadLocalJSON.loadJSONFromAsset(netManager.applicationContext, fileJsonNm)
            } catch (e: Exception) {
                theJson = null
            }

            return theJson
        }

        override fun onPostExecute(jsonObject: JSONObject?) {

            convertToGson(jsonObject)

        }
    }

    fun  convertToGson(jsonObject: JSONObject?) {

        var gs = Gson()
        val example =  gs.fromJson<Example>(jsonObject.toString(), Example::class.java)
        handlr.onDataReady(example)

    }

    interface OnBitcointDataReady{
        fun onDataReady(data : Example?)
    }

}