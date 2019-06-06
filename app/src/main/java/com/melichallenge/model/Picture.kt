package com.melichallenge.model

import android.os.Parcel
import android.os.Parcelable

/**
 *
 * Created by maizaga on 2019-06-05.
 */
data class Picture(val pictureId: String,
                   val url: String,
                   val sizeWidth: Int,
                   val sizeHeight: Int,
                   val maxSizeWidth: Int,
                   val maxSizeHeight: Int): Parcelable {

    private constructor(p: Parcel): this(
        p.readString() ?: "",
        p.readString() ?: "",
        p.readInt(),
        p.readInt(),
        p.readInt(),
        p.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(pictureId)
        parcel.writeString(url)
        parcel.writeInt(sizeWidth)
        parcel.writeInt(sizeHeight)
        parcel.writeInt(maxSizeWidth)
        parcel.writeInt(maxSizeHeight)
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField val CREATOR = object : Parcelable.Creator<Picture> {
            override fun createFromParcel(parcel: Parcel) = Picture(parcel)

            override fun newArray(size: Int) = arrayOfNulls<Picture>(size)
        }

        fun pictureFrom(response: PictureResponse) = with(response) {
            val sizes = size.split("x")
            val maxSizes = maxSize.split("x")

            return@with when {
                sizes.size == 2 && maxSizes.size == 2 -> Picture(pictureId, url, sizes[0].toInt(), sizes[1].toInt(),
                        maxSizes[0].toInt(), maxSizes[1].toInt())
                else -> Picture(pictureId, url, 0, 0, 0, 0)
            }
        }
    }
}