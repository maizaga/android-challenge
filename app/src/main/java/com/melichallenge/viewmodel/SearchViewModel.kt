package com.melichallenge.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.melichallenge.data.ItemsRepository
import com.melichallenge.model.ItemHeader
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 *
 * Created by maizaga on 2019-06-06.
 */
class SearchViewModel @Inject constructor(private val itemsRepository: ItemsRepository): ViewModel() {

    private val disposables = CompositeDisposable()
    private val subject = PublishSubject.create<String>()

    private val _searchData = MutableLiveData<ArrayList<ItemHeader>>()
    val searchData: LiveData<ArrayList<ItemHeader>>
        get() = _searchData
    private val _errorData = MutableLiveData<String>()
    val errorData: LiveData<String>
        get() = _errorData

    val progressLoading = ObservableBoolean()

    fun onSearchTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        subject.onNext(s.toString())
    }

    fun search() {
        disposables.add(
            subject
                .debounce(700, TimeUnit.MILLISECONDS)
                .filter { it.isNotEmpty() }
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { progressLoading.set(true) }
                .switchMap(Function<String, ObservableSource<ArrayList<ItemHeader>>> {
                    return@Function itemsRepository.searchItems(it)
                })
                .subscribe({
                    _searchData.value = it
                    progressLoading.set(false)
                }, {
                    _errorData.value = it.localizedMessage
                    progressLoading.set(false)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()

        disposables.clear()
    }
}