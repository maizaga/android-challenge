package com.melichallenge.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.melichallenge.R
import com.melichallenge.model.Item
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()

        supportFragmentManager.findFragmentByTag(SEARCH_TAG) as? SearchFragment ?: run {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, SearchFragment.newInstance(), SEARCH_TAG)
                .commit()
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun setTitle(title: String) {
        toolbarTitle.text = title
    }

    fun navigateToItem(itemId: String) {
        supportFragmentManager.findFragmentByTag(ITEM_TAG) as? ItemFragment ?: run {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, ItemFragment.newInstance(itemId), ITEM_TAG)
                .addToBackStack(ITEM_TAG)
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> = dispatchingAndroidInjector

    companion object {
        private const val SEARCH_TAG = "searchTag"
        private const val ITEM_TAG = "itemTag"
    }
}
