package com.example.diseasedirectory.disease

import android.os.Parcel
import android.os.Parcelable

data class Disease(val title: String, val info: String, val type: String, val images: List<String>,
    val reasons: String, val classifications: String, val symptoms: String, val diagnostics: String, val category: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(info)
        parcel.writeString(type)
        parcel.writeStringList(images)
        parcel.writeString(reasons)
        parcel.writeString(classifications)
        parcel.writeString(symptoms)
        parcel.writeString(diagnostics)
        parcel.writeString(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Disease> {
        override fun createFromParcel(parcel: Parcel): Disease {
            return Disease(parcel)
        }

        override fun newArray(size: Int): Array<Disease?> {
            return arrayOfNulls(size)
        }
    }
}
