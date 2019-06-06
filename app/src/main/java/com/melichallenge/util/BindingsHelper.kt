package com.melichallenge.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.melichallenge.model.ItemHeader
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * Created by maizaga on 2019-06-04.
 */
object BindingsHelper {

    @JvmStatic
    fun formatDate(date: Date): String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)

    @BindingAdapter("imageLoader")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context).load(url).into(imageView)
    }

    @JvmStatic
    fun formatPrice(item: ItemHeader) = when (item.currencyId) {
        "ARS" -> "$ ${item.price}"
        else -> "${item.currencyId} ${item.price}"
    }

    @JvmStatic
    fun capitalize(text: String) = text.capitalize()

    @JvmStatic
    fun twoDotsEnd(text: String) = "$text:"
}