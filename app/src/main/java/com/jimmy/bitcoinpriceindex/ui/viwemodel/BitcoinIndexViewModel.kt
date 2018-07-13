package com.jimmy.bitcoinpriceindex.ui.viwemodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.jimmy.bitcoinpriceindex.data.BitcoinIndexRepository
import com.jimmy.bitcoinpriceindex.data.models.Example
import com.jimmy.bitcoinpriceindex.managers.NetManager

/*
    AndroidViewModel from Lifecycle-aware components library that has context.
    That context is context of the application, not of an Activity

 */
class BitcoinIndexViewModel(application :Application) : AndroidViewModel(application){

    val nm = NetManager(getApplication())
    val bitconIndxRepository = BitcoinIndexRepository(nm)

    var  example : MutableLiveData<Example> = MutableLiveData()


    fun getBitcoinPriceRepoData(){
        bitconIndxRepository.getRepositories(object : BitcoinIndexRepository.OnBitcointDataReady {
            override fun onDataReady(data: Example?) {
                example.value = data
            }
        })
    }
}
