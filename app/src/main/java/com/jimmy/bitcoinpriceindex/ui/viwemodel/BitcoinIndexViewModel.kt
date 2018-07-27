package com.jimmy.bitcoinpriceindex.ui.viwemodel

import android.app.Application
import android.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jimmy.bitcoinpriceindex.Constants
import com.jimmy.bitcoinpriceindex.data.BitcoinIndexRepository
import com.jimmy.bitcoinpriceindex.data.models.Example
import com.jimmy.bitcoinpriceindex.data.models.StatusModel
import com.jimmy.bitcoinpriceindex.managers.NetManager

/*
    AndroidViewModel from Lifecycle-aware components library that has context.
    That context is context of the application, not of an Activity

 */
class BitcoinIndexViewModel(application : Application) : AndroidViewModel(application){

    val nm = NetManager(this.getApplication())
    val bitconIndxRepository = BitcoinIndexRepository(nm)

    var  example : MutableLiveData<Example> = MutableLiveData()
    var  status : MutableLiveData<Int> = MutableLiveData()

    // ObservableField(T val)  Wraps the given object and creates an observable object with default value
    val text = ObservableField("Tap icon to load data")
    val isLoading = ObservableField(false)
    val loadStatus = ObservableField(Constants.SUCCESS_LOAD)

    var statusModel  = StatusModel(Constants.SUCCESS_LOAD)


    fun getBitcoinPriceRepoData(){

        isLoading.set(true)
        text.set("Loading bitcoin data")
        bitconIndxRepository.getRepositories(object : BitcoinIndexRepository.OnBitcointDataReady {
            override fun onDataReady(data: Example?, code: Int) {

                isLoading.set(false)
                status.value = code
                text.set("Tap icon to load data")
                loadStatus.set(code)
                statusModel.statusCode = code
                example.value = data

/* just testing how to use when block
   when (code) {
        Constants.OFFLINE_LOAD -> {
            status.value = 100
            text.set("saved data from data store")
        }

        Constants.SUCCESS_LOAD ->  {
            status.value = 200
            text.set("horrrayyyyyyy")
        }

        Constants.FAIL_TO_LOAD_ERROR ->  {
            status.value = 300
            text.set(":(((((((")
        }
    }
*/
            }

        })
    }
}
