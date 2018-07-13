package com.jimmy.bitcoinpriceindex.ui.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.jimmy.bitcoinpriceindex.R
import com.jimmy.bitcoinpriceindex.data.models.Example
import com.jimmy.bitcoinpriceindex.databinding.BitcoinIndexActivityBinding
import com.jimmy.bitcoinpriceindex.ui.viwemodel.BitcoinIndexViewModel

class BitcoinIndexActivity : AppCompatActivity() {

    private lateinit var mViewModel: BitcoinIndexViewModel

    lateinit var binding : BitcoinIndexActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.bitcoin_index_activity)

        // Get the ViewModel
        mViewModel = ViewModelProviders.of(this).get(BitcoinIndexViewModel::class.java)


        binding.viewmodel = mViewModel

        /*
         Evaluates the pending bindings, updating any Views that have expressions bound to modified
         variables. This must be run on the UI thread.
         */
        binding.executePendingBindings()


        Handler().postDelayed( {
            mViewModel.getBitcoinPriceRepoData()
        },15000 )


        mViewModel.example.observe(this,  Observer<Example>{
            Log.e("KKKKKKKKKK", it?.disclaimer)
        })
    }
}
