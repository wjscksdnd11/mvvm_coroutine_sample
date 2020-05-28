package com.jeon.listsample.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.jeon.listsample.R

object BindingAdapter {

    @BindingAdapter("bind:glide")
    @JvmStatic
    fun setGlide(imageView: ImageView, iconId:String?){
        iconId?.let {
            val url = imageView.context.getString(R.string.weather_url,iconId)
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}
