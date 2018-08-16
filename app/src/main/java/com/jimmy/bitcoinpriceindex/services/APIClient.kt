package com.jimmy.bitcoinpriceindex.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
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

       /*     val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build()*/

            val client = getUnsafeOkHttpClient()
                    ?.addInterceptor(interceptor)
                    ?.readTimeout(30, TimeUnit.SECONDS)
                    ?.connectTimeout(30, TimeUnit.SECONDS)
                    ?.build()

            retrofit = Retrofit.Builder()
                    .baseUrl("https://api.coindesk.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

            return retrofit!!

        }

        /**
         * enables okhttp to accept untrusted certificates (when behind firewall or proxy)
         */
        fun getUnsafeOkHttpClient() : OkHttpClient.Builder?{

             lateinit var builder : OkHttpClient.Builder

            try {

                val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager{
                    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                       //
                    }

                    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                      //
                    }
                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }

                })
                // Create a trust manager that does not validate certificate chains
                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())

                // Create an ssl socket factory with our all-trusting manager
                val  sslSocketFactory : SSLSocketFactory = sslContext.socketFactory

                builder = OkHttpClient.Builder()
                builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)

                builder.hostnameVerifier { hostname, session -> true }
                return builder
            } catch ( e : Exception) {
//                throw  RuntimeException(e)
                println("OKHTTP error >>>>>>>>>>>> ${e.message}")
                return OkHttpClient.Builder()
            }

        }
    }

}
