package com.jimmy.bitcoinpriceindex

object Constants {

    // Notification Channel constants

    // Name of Notification Channel for verbose notifications of background work
    const val VERBOSE_NOTIFICATION_CHANNEL_NAME = "Verbose WorkManager Notifications"
    const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION = "Shows notifications whenever work starts"
    const val NOTIFICATION_TITLE = "WorkRequest Starting"
    const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
    const val NOTIFICATION_ID = 1

    const val DELAY_TIME_MILLIS: Long = 3000

    const val BPI_ENDPOINT = "https://api.coindesk.com/v1/bpi/currentprice.json"
}// Ensures this class is never instantiated