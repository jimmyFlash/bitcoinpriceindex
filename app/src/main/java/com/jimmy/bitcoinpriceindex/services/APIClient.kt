package com.jimmy.bitcoinpriceindex.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import javax.net.ssl.*

/**
 * Created by jamal.safwat on 1/7/2018.
 */

class APIClient {

    // defining static function / variables using companion objects
    companion object {

        private var retrofit: Retrofit? = null

        fun getClient() : Retrofit{

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

            retrofit = Retrofit.Builder()
                    .baseUrl("https://api.coindesk.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

            return retrofit!!

        }
    }

}
