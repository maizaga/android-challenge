package com.melichallenge.model

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * Created by maizaga on 2019-06-05.
 */
open class ItemHeader(
    val itemId: String,
    val title: String,
    val price: Float,
    val currencyId: String,
    val condition: String,
    val thumbnail: String,
    val freeShipping: Boolean
): Parcelable {

    private constructor(p: Parcel): this(
        p.readString() ?: "",
        p.readString() ?: "",
        p.readFloat(),
        p.readString() ?: "",
        p.readString() ?: "",
        p.readString() ?: "",
        p.readByte() == 1.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(itemId)
        parcel.writeString(title)
        parcel.writeFloat(price)
        parcel.writeString(currencyId)
        parcel.writeString(condition)
        parcel.writeString(thumbnail)
        parcel.writeByte((if (freeShipping) 1 else 0).toByte())
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField val CREATOR = object : Parcelable.Creator<ItemHeader> {
            override fun createFromParcel(parcel: Parcel) = ItemHeader(parcel)

            override fun newArray(size: Int) = arrayOfNulls<ItemHeader>(size)
        }
    }
}