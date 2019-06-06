package com.melichallenge.model

import com.google.gson.annotations.SerializedName

/**
 *
 * Created by maizaga on 2019-06-05.
 */
data class SearchResponse(
    @SerializedName("site_id") val siteId: String,
    @SerializedName("query") val query: String,
    @SerializedName("paging") val paging: Pagination,
    @SerializedName("results") val results: ArrayList<SearchItemResponse>
)

data class Pagination(
    @SerializedName("total") val total: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("primary_results") val primaryResults: Int
)

data class SearchItemResponse(
    @SerializedName("id") val itemId: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Float,
    @SerializedName("currency_id") val currencyId: String,
    @SerializedName("condition") val condition: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("shipping") val shipping: Shipping
)

data class Shipping(
    @SerializedName("free_shipping") val freeShipping: Boolean
)