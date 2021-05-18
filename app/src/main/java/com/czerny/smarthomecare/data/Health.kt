package com.czerny.smarthomecare.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Health(
    var id: String = "",
    var place: String = "",
    var title: String = "",
    var name: String = "",
    var content: String = "",
    var note: String = "",
    var createdTime: Long = -1
) : Parcelable