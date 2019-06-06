package com.melichallenge.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager.widget.ViewPager
import com.melichallenge.data.ItemsRepository
import com.melichallenge.model.Item
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 *
 * Created by maizaga on 2019-06-06.
 */
class ItemViewModel @Inject constructor(private val itemsRepository: ItemsRepository): ViewModel() {
    private val disposables = CompositeDisposable()

    private val _itemData = MutableLiveData<Item>()
    val itemData: LiveData<Item>
        get() = _itemData
    private val _errorData = MutableLiveData<String>()
    val errorData: LiveData<String>
        get() = _errorData

    val progressLoading = ObservableBoolean()
    val selectedTab = ObservableInt()

    val pageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) { }
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }
        override fun onPageSelected(position: Int) {
            selectedTab.set(position)
        }
    }

    fun getItem(itemId: String) {
        disposables.add(itemsRepository.getItem(itemId)
            .doOnSubscribe { progressLoading.set(true) }
            .doOnTerminate {
                progressLoading.set(false)
            }
            .subscribe({
                _itemData.value = it
            }, {
                _errorData.value = it.localizedMessage
            }))
    }

    override fun onCleared() {
        super.onCleared()

        disposables.clear()
    }
}