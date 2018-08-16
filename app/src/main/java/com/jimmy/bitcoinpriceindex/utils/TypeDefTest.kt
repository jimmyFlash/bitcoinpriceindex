package com.jimmy.bitcoinpriceindex.utils

import android.support.annotation.IntDef

class TypeDefTest(@ITEM item : Int) {// type def annotated parameter

    companion object {
        const val ITEM_SERVICES = 0
        const val ITEM_PORTFOLIO = 1
    }


    // IntDef for types
    @IntDef(ITEM_SERVICES, ITEM_PORTFOLIO)
    @Retention(AnnotationRetention.SOURCE)
    annotation class ITEM // for kotlin this is the interface name annotation


    // annotated property
    @ITEM
    private var speed: Int = ITEM_SERVICES

    // type annotated fun parameter
    fun setSpeed(@ITEM speed: Int) {
        this.speed = speed
        println("Speed updated to >>>>>>>>>>>> $speed")
    }
}