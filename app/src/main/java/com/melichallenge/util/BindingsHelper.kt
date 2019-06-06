package com.melichallenge.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.melichallenge.model.ItemHeader
import com.melichallenge.model.SellerAddress
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * Created by maizaga on 2019-06-04.
 */
object BindingsHelper {

    @BindingAdapter("imageLoader")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context).load(url).into(imageView)
    }

    @BindingAdapter("pageListener")
    @JvmStatic
    fun setOnPageChangedListener(viewPager: ViewPager, listener: ViewPager.OnPageChangeListener) {
        viewPager.clearOnPageChangeListeners()
        viewPager.addOnPageChangeListener(listener)
    }

    @BindingAdapter("selectTab")
    @JvmStatic
    fun selectTab(tabLayout: TabLayout, position: Int) {
        tabLayout.getTabAt(position)?.select()
    }

    @JvmStatic
    fun locationText(sellerAddress: SellerAddress?) = sellerAddress?.let { "${it.city}, ${it.state}" } ?: "Not Found"

    @JvmStatic
    fun formatPrice(currencyId: String?, price: Float?) = when (currencyId) {
        "ARS" -> "$ $price"
        else -> "${currencyId ?: "$"} ${price ?: 0.0f}"
    }

    @JvmStatic
    fun capitalize(text: String?) = text?.capitalize()

    @JvmStatic
    fun twoDotsEnd(text: String) = "$text:"
}