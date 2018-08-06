package com.jimmy.bitcoinpriceindex.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log

import com.jimmy.bitcoinpriceindex.Constants
import com.jimmy.bitcoinpriceindex.R
import java.util.*






object UIUtils {

    val TAG = UIUtils::class.java.simpleName

    var currencyLocaleMap: SortedMap<Currency, Locale>? = null

    init {// static initalizer block in kotlin

       currencyLocaleMap = TreeMap< Currency, Locale >(Comparator<Currency> { c1, c2
           -> c1.currencyCode.compareTo(c2.currencyCode) })

       for (locale : Locale in Locale.getAvailableLocales()) {
           try {
               val currency = Currency.getInstance(locale)
               (currencyLocaleMap as TreeMap<Currency, Locale>)[currency] = locale
           } catch (e: Exception) {
               //
           }
       }

    }

    fun getCurrencySymbol (currencyCode: String):String{
        val currency = Currency.getInstance(currencyCode)
        return currency.getSymbol(currencyLocaleMap?.get(currency))
    }


    /**
     * Method for sleeping for a fixed about of time to emulate slower work
     */
    fun sleep() {
        try {
            Thread.sleep(Constants.DELAY_TIME_MILLIS, 0)
        } catch (e: InterruptedException) {
            Log.d(TAG, e.message)
        }
    }

    /**
     * Create a Notification that is shown as a heads-up notification if possible.
     *
     * For this codelab, this is used to show a notification so that you know when different steps
     * of the background work chain are starting
     *
     * @param message Message shown on the notification
     * @param context Context needed to create Toast
     */
    fun makeStatusNotification(message: String, context: Context) {

        // Make a channel if necessary
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            val name = Constants.VERBOSE_NOTIFICATION_CHANNEL_NAME
            val description = Constants.VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(Constants.CHANNEL_ID, name, importance)
            channel.description = description

            // Add the channel
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }

        // Create the notification
        val builder = NotificationCompat.Builder(context, Constants.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(Constants.NOTIFICATION_TITLE)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(LongArray(0))

        // Show the notification
        NotificationManagerCompat.from(context).notify(Constants.NOTIFICATION_ID, builder.build())
    }



}
