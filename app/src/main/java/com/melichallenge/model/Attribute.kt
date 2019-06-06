package com.melichallenge.model

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * Created by maizaga on 2019-06-05.
 */
data class Attribute(val attributeId: String, val name: String, val valueName: String): Parcelable {
    private constructor(p: Parcel): this(
        p.readString() ?: "",
        p.readString() ?: "",
        p.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(attributeId)
        parcel.writeString(name)
        parcel.writeString(valueName)
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField val CREATOR = object : Parcelable.Creator<Attribute> {
            override fun createFromParcel(parcel: Parcel) = Attribute(parcel)

            override fun newArray(size: Int) = arrayOfNulls<Attribute>(size)
        }
    }
}