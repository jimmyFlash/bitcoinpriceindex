package com.jimmy.bitcoinpriceindex.data

import android.content.Context

import org.json.JSONException
import org.json.JSONObject

import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

object LoadLocalJSON {


    fun loadJSONFromAsset(cntxt: Context, file: String): JSONObject? {
        var json: String? = null
        var jsoonOb: JSONObject? = null
        try {
            val iStream = cntxt.assets.open("$file.json")
            val size = iStream.available()
            val buffer = ByteArray(size)
            iStream.read(buffer)
            iStream.close()
            json = String(buffer, Charset.defaultCharset())
            jsoonOb = JSONObject(json)
        } catch (ex: IOException) {
            ex.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return jsoonOb
    }

}
