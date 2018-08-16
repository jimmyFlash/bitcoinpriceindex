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
    const val FAIL_TO_LOAD_ERROR: Int = 500
    const val SUCCESS_LOAD: Int = 100
    const val OFFLINE_LOAD: Int = 300

    const val BPI_ENDPOINT = "https://api.coindesk.com/v1/bpi/currentprice.json"


    // sample of emoji
    // [U+1F469] (WOMAN) + [U+200D] (ZERO WIDTH JOINER) + [U+1F4BB] (PERSONAL COMPUTER)
    private val WOMAN_TECHNOLOGIST = "\uD83D\uDC69\u200D\uD83D\uDCBB"

    // [U+1F469] (WOMAN) + [U+200D] (ZERO WIDTH JOINER) + [U+1F3A4] (MICROPHONE)
    public val WOMAN_SINGER = "\uD83D\uDC69\u200D\uD83C\uDFA4"

    @JvmField
    val EMOJI = WOMAN_TECHNOLOGIST + " " + WOMAN_SINGER





}// Ensures this class is never instantiated