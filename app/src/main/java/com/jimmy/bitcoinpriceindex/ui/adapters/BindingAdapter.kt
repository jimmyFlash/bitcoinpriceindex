package com.jimmy.bitcoinpriceindex.ui.adapters

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.jimmy.bitcoinpriceindex.Constants
import com.jimmy.bitcoinpriceindex.R


/**
 * defining s static function in kotlin using object keyword
 */

    @BindingAdapter(value = ["iconType"])
    fun ImageView.bindImageResource(statusCode: Int){
        when (statusCode) {
            Constants.SUCCESS_LOAD -> setImageResource(R.drawable.ic_launcher_foreground)
            Constants.FAIL_TO_LOAD_ERROR ->  setImageResource(R.drawable.ic_launcher_foreground)
            Constants.OFFLINE_LOAD ->  setImageResource(R.drawable.ic_launcher_foreground)
        }

       /* Glide.with(this.context)
                .load(R.raw.loader)
                .error(R.drawable.ic_launcher_background)
                .into(this)*/
    }

/*
    *//**
     * Bind Glide with an ImageView.
     *
     * @param view        the ImageView to bind to Glide.
     * @param src         The URL of the image to load.
     * @param placeholder The placeholder icon.
     * @param error       The error icon.
     * @param blurValue   The blur radius value between 1 and 25.
     * @param cropCircle  Crop the image in a circle of not.
     *//*
    @BindingAdapter(value = ["src", "placeholder", "error", "blur", "cropCircle"], requireAll = false)
    fun setGlideAdapter(view: ImageView?, src: String, placeholder: Drawable?,
                        error: Drawable?, blurValue: Int, cropCircle: Boolean) {
        if (view != null) {
            val ctx = view.context
            val glideBuilder = Glide.with(ctx).load(src)
            val filters : MutableList<Transformation<Bitmap>> = ArrayList()
            if (placeholder != null) {
                glideBuilder.placeholder(placeholder)
            }
            if (error != null) {
                glideBuilder.error(error)
            }

            if (blurValue > 0) {
                filters.add(BlurTransformation(ctx, blurValue))
            }

            if (cropCircle) {
                filters.add(CropCircleTransformation(ctx))
            }
            if (filters.size > 0) {
                var filtersArr: Array<Transformation<Bitmap>> = arrayOfNulls<Transformation>(filters.size)
                filtersArr = filters.toTypedArray()
                glideBuilder.bitmapTransform(filtersArr)
            }

            glideBuilder.into(view)
        }
    }
*/
