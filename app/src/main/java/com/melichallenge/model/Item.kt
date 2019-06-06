package com.melichallenge.model

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * Created by maizaga on 2019-06-05.
 */
class Item(
    itemId: String,
    title: String,
    price: Float,
    currencyId: String,
    condition: String,
    thumbnail: String,
    freeShipping: Boolean,
    val pictures: ArrayList<Picture>,
    val availableQuantity: Int,
    val sellerAddress: SellerAddress,
    val attributes: ArrayList<Attribute>
) : ItemHeader(itemId, title, price, currencyId, condition, thumbnail, freeShipping), Parcelable {
    private constructor(p: Parcel): this(
        p.readString() ?: "",
        p.readString() ?: "",
        p.readFloat(),
        p.readString() ?: "",
        p.readString() ?: "",
        p.readString() ?: "",
        p.readByte() == 1.toByte(),
        arrayListOf<Picture>().apply {
            p.readList(this, Picture::class.java.classLoader)
        },
        p.readInt(),
        p.readParcelable<SellerAddress>(SellerAddress::class.java.classLoader)!!,
        arrayListOf<Attribute>().apply {
            p.readList(this, Attribute::class.java.classLoader)
        }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeList(pictures)
        parcel.writeInt(availableQuantity)
        parcel.writeParcelable(sellerAddress, flags)
        parcel.writeList(attributes)
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField val CREATOR = object : Parcelable.Creator<Item> {
            override fun createFromParcel(parcel: Parcel) = Item(parcel)

            override fun newArray(size: Int) = arrayOfNulls<Item>(size)
        }
    }
}