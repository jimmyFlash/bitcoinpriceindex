package com.jimmy.bitcoinpriceindex.utils

import android.support.annotation.IntDef

object LoadStatusTypeDef{

    const val FAIL_TO_LOAD_ERROR: Int = 500
    const val SUCCESS_LOAD: Int = 100
    const val OFFLINE_LOAD: Int = 300

    @IntDef(FAIL_TO_LOAD_ERROR, SUCCESS_LOAD, OFFLINE_LOAD)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Status // for kotlin this is the interface name annotation
}