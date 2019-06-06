package com.melichallenge.data

import com.melichallenge.model.ItemHeader
import com.melichallenge.model.ItemResponse
import com.melichallenge.model.SearchResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * Created by maizaga on 2019-06-05.
 */
@Singleton
class ItemsRepository @Inject constructor(retrofit: Retrofit) {
    private val itemsApi = retrofit.create(ItemsApi::class.java)

    fun searchItems(query: String): Observable<ArrayList<ItemHeader>> = itemsApi.searchItems(query)
        .flatMap {
            when {
                it.body() is SearchResponse -> Observable.fromCallable { Mapper.mapSearchResponse(it.body()!!) }
                else -> throw HttpException(it)
            }
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getItem(itemId: String) = itemsApi.getItem(itemId)
        .flatMap {
            when {
                it.body() is ItemResponse -> Observable.fromCallable { Mapper.mapItemResponse(it.body()!!) }
                else -> throw HttpException(it)
            }
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}