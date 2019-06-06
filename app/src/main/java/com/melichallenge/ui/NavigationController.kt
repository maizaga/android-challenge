package com.melichallenge.ui

import com.melichallenge.R
import javax.inject.Inject

/**
 *
 * Created by maizaga on 2019-06-06.
 */
class NavigationController @Inject constructor(private val activity: MainActivity) {
    private val containerId = R.id.content

    fun initNavigation() {
        activity.supportFragmentManager.findFragmentByTag(SEARCH_TAG) as? SearchFragment ?: run {
            activity.supportFragmentManager
                .beginTransaction()
                .replace(containerId, SearchFragment.newInstance(), SEARCH_TAG)
                .commit()
        }
    }

    fun navigateToItem(itemId: String) {
        activity.supportFragmentManager.findFragmentByTag(ITEM_TAG) as? ItemFragment ?: run {
            activity.supportFragmentManager
                .beginTransaction()
                .replace(containerId, ItemFragment.newInstance(itemId), ITEM_TAG)
                .addToBackStack(ITEM_TAG)
                .commit()
        }
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        private const val SEARCH_TAG = "searchTag"
        private const val ITEM_TAG = "itemTag"
    }
}