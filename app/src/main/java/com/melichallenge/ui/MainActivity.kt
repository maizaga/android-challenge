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
    @Inject
    lateinit var navigationController: NavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        navigationController.initNavigation()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun setTitle(title: String) {
        toolbarTitle.text = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> = dispatchingAndroidInjector
}
