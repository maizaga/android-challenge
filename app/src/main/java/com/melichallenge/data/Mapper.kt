package com.melichallenge.data

import com.melichallenge.model.*

/**
 *
 * Created by maizaga on 2019-06-05.
 */
object Mapper {

    fun mapSearchResponse(searchResponse: SearchResponse): ArrayList<ItemHeader> = with(searchResponse) {
        return@with ArrayList(results.map {
            ItemHeader(it.itemId, it.title, it.price, it.currencyId, it.condition, it.thumbnail, it.shipping.freeShipping)
        })
    }

    fun mapItemResponse(itemResponse: ItemResponse): Item = with(itemResponse) {
        Item(itemId, title, price, currencyId, condition, thumbnail, shipping.freeShipping,
            mapPicturesResponse(pictures), availableQuantity, SellerAddress.addressFrom(sellerAddress),
            mapAttributes(attributes))
    }

    private fun mapPicturesResponse(picturesResponse: ArrayList<PictureResponse>) = ArrayList(picturesResponse.map {
        Picture.pictureFrom(it)
    })

    private fun mapAttributes(attributesResponse: ArrayList<AttributeResponse>) = ArrayList(attributesResponse.filter {
        !it.valueName.isNullOrEmpty()
    }.map {
        Attribute(it.attributeId, it.name, it.valueName!!)
    })
}