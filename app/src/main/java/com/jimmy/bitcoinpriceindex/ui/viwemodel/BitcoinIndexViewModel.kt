package com.jimmy.bitcoinpriceindex.ui.viwemodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
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

    // ObservableField(T val)  Wraps the given object and creates an observable object with default value
    val text = ObservableField("Press icon to load data")
    val isLoading = ObservableField(false)


    fun getBitcoinPriceRepoData(){

        isLoading.set(true)
        text.set("Loading bitcoin data")

        bitconIndxRepository.getRepositories(object : BitcoinIndexRepository.OnBitcointDataReady {
            override fun onDataReady(data: Example?) {
                isLoading.set(false)
                text.set("Updated")
                example.value = data
            }
        })
    }
}
