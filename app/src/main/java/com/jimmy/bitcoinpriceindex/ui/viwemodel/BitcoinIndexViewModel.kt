package com.jimmy.bitcoinpriceindex.ui.viwemodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.jimmy.bitcoinpriceindex.Constants
import com.jimmy.bitcoinpriceindex.R
import com.jimmy.bitcoinpriceindex.data.BitcoinIndexRepository
import com.jimmy.bitcoinpriceindex.data.models.Example
import com.jimmy.bitcoinpriceindex.data.models.StatusModel
import com.jimmy.bitcoinpriceindex.managers.NetManager
import com.jimmy.bitcoinpriceindex.utils.UIUtils

/*
    AndroidViewModel from Lifecycle-aware components library that has context.
    That context is context of the application, not of an Activity

 */
class BitcoinIndexViewModel(application :Application) : AndroidViewModel(application){

    val nm = NetManager(this.getApplication())
    val bitconIndxRepository = BitcoinIndexRepository(nm)

    var  example : MutableLiveData<Example> = MutableLiveData()
    var  status : MutableLiveData<Int> = MutableLiveData()
    val ctx = this.getApplication<Application>()

    // ObservableField(T val)  Wraps the given object and creates an observable object with default value
    val text = ObservableField(ctx.getString(R.string.tap_msg))
    val isLoading = ObservableField(false)
    val loadStatus = ObservableField(Constants.SUCCESS_LOAD)
    var statusModel  = StatusModel(Constants.SUCCESS_LOAD)


    fun getBitcoinPriceRepoData(){

        isLoading.set(true)
        text.set(ctx.getString(R.string.load_data))
        bitconIndxRepository.getRepositories(object : BitcoinIndexRepository.OnBitcointDataReady {
            override fun onDataReady(data: Example?, code: Int) {

                isLoading.set(false)
                status.value = code

//                Html.fromHtml("&#x2713;", Html.FROM_HTML_MODE_LEGACY)
                //todo method to display currency symbol from html
                val stringBuilder = StringBuilder()

                stringBuilder.append("Last Update: ${data?.time?.updated} :\n ")
                        .append("\n${data?.bpi?.uSD?.code} : ${data?.bpi?.uSD?.code?.let { UIUtils.getCurrencySymbol(it) }} " +
                                "${data?.bpi?.uSD?.rate}")
                        .append("\n${data?.bpi?.eUR?.code} : ${data?.bpi?.eUR?.code?.let { UIUtils.getCurrencySymbol(it) }}" +
                                " ${data?.bpi?.eUR?.rate}")
                        .append("\n${data?.bpi?.gBP?.code} : ${data?.bpi?.gBP?.code?.let { UIUtils.getCurrencySymbol(it) }}" +
                                " ${data?.bpi?.gBP?.rate}")

                text.set(stringBuilder.toString())
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
