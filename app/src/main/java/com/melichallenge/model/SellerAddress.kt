package com.melichallenge.model

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * Created by maizaga on 2019-06-05.
 */
data class SellerAddress(val city: String, val state: String): Parcelable {
    private constructor(p: Parcel): this(
        p.readString() ?: "",
        p.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(city)
        parcel.writeString(state)
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField val CREATOR = object : Parcelable.Creator<SellerAddress> {
            override fun createFromParcel(parcel: Parcel) = SellerAddress(parcel)

            override fun newArray(size: Int) = arrayOfNulls<SellerAddress>(size)
        }

        fun addressFrom(sellerAddressResponse: SellerAddressResponse) = with(sellerAddressResponse) {
            SellerAddress(city.name, state.name)
        }
    }
}