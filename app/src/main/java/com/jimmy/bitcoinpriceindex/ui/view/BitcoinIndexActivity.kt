package com.jimmy.bitcoinpriceindex.ui.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.graphics.drawable.VectorDrawableCompat
import android.support.text.emoji.EmojiCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.jimmy.bitcoinpriceindex.R
import com.jimmy.bitcoinpriceindex.data.models.Example
import com.jimmy.bitcoinpriceindex.databinding.BitcoinIndexActivityBinding
import com.jimmy.bitcoinpriceindex.ui.viwemodel.BitcoinIndexViewModel
import java.lang.ref.WeakReference

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



        // Regular TextView without EmojiCompat support; you have to manually process the text
        EmojiCompat.get().registerInitCallback(InitCallback(binding.regularTextView))

        Log.e(";;;;;", StringBuilder().appendCodePoint(0x1F602).toString())


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

    /**
     * Main class to keep Android devices up to date with the newest emojis by adding EmojiSpans to
     * a given CharSequence. It is a singleton class that can be configured using a
     * EmojiCompat.Config instance.
     * EmojiCompat has to be initialized using init(EmojiCompat.Config) function before it can
     * process a CharSequence.
     * EmojiCompat.init(/* a config instance */); usually done in project's application class
     *
     */
    private class InitCallback(regularTextView: TextView) : EmojiCompat.InitCallback() {

        val atom =  StringBuilder().appendCodePoint(0x269B).toString()
        val gemini =  StringBuilder().appendCodePoint(0x264A).toString()

        val regularTextViewRef = WeakReference(regularTextView)

        override fun onInitialized() {
            val regularTextView = regularTextViewRef.get()
            if (regularTextView != null) {
                val compat = EmojiCompat.get()
                val context = regularTextView.context
                //regularTextView.text = compat.process( context.getString(R.string.regular_text_view, EMOJI)
                regularTextView.text = compat.process( context.getString(R.string.regular_text_view, "$atom$gemini " )
                )
            }
        }

    }

}
