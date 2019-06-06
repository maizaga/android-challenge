package com.melichallenge.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.melichallenge.R
import com.melichallenge.model.Picture
import kotlinx.android.synthetic.main.pager_item_image.view.*

/**
 *
 * Created by maizaga on 2019-06-06.
 */
class PicturePagerAdapter: PagerAdapter() {
    var items = arrayListOf<Picture>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = items.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageLayout = LayoutInflater.from(container.context).inflate(R.layout.pager_item_image, container, false)

        Glide.with(imageLayout.context).load(items[position].url).into(imageLayout.pagerItemPicture)
        container.addView(imageLayout)

        return imageLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}