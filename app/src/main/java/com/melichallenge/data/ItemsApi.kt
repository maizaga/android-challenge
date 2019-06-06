package com.melichallenge.data

import com.melichallenge.model.ItemResponse
import com.melichallenge.model.SearchResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
 * Created by maizaga on 2019-06-05.
 */
interface ItemsApi {

    @GET("sites/MLA/search")
    fun searchItems(@Query("q") text: String): Observable<Response<SearchResponse>>

    @GET("items/{itemId}")
    fun getItem(@Path("itemId") itemId: String): Observable<Response<ItemResponse>>
}