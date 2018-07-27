package com.jimmy.bitcoinpriceindex.ui.view



import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jimmy.bitcoinpriceindex.R
import com.jimmy.bitcoinpriceindex.data.models.Example
import com.jimmy.bitcoinpriceindex.databinding.BitcoinIndexActivityBinding
import com.jimmy.bitcoinpriceindex.ui.viwemodel.BitcoinIndexViewModel
//import com.jimmy.bitcoinpriceindex.utils.UIUtils

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
        },18000 )


        mViewModel.example.observe(this,  Observer<Example>{
            Log.e("KKKKKKKKKK", it?.disclaimer)
        })

        mViewModel.status.observe(this,  Observer<Int>{
            Log.e("MMMMMMMMMMM", "$it")
        })
    }
}
