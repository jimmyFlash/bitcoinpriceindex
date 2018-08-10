package com.jimmy.bitcoinpriceindex.ui.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Resources
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.graphics.drawable.VectorDrawableCompat
import android.util.Log
import com.jimmy.bitcoinpriceindex.R
import com.jimmy.bitcoinpriceindex.data.models.Example
import com.jimmy.bitcoinpriceindex.databinding.BitcoinIndexActivityBinding
import com.jimmy.bitcoinpriceindex.ui.viwemodel.BitcoinIndexViewModel

class BitcoinIndexActivity : AppCompatActivity() {



    private lateinit var mViewModel: BitcoinIndexViewModel

    lateinit var binding : BitcoinIndexActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        //todo add implementation for Room and save prices to it
        //todo use workmanager to store updated prices in room DB for offline
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.bitcoin_index_activity)

        // Get the ViewModel
        mViewModel = ViewModelProviders.of(this).get(BitcoinIndexViewModel::class.java)


        binding.viewmodel = mViewModel

        // set VectorDrawable for pre-Lollipop devices
        val resources = resources
        val theme = theme
        val drawable = VectorDrawableCompat.create(resources, R.drawable.ic_bitcoin, theme)
        binding.logo.setImageDrawable(drawable)

        /*
         Evaluates the pending bindings, updating any Views that have expressions bound to modified
         variables. This must be run on the UI thread.
         */
        binding.executePendingBindings()


        // use timed handler for debugging purposes
       /* Handler().postDelayed( {
            mViewModel.getBitcoinPriceRepoData()
        },18000 )
        */

        // initialize call to server with click listener on logo img
        binding.logo.setOnClickListener {
            mViewModel.getBitcoinPriceRepoData()
        }


        mViewModel.example.observe(this,  Observer<Example>{
            Log.e("Disclaimer observer", it?.disclaimer)

        })

        mViewModel.status.observe(this,  Observer<Int>{
            Log.e("Status observer", "$it")
        })

        //initialize and save date and data to room DB
    }
}
