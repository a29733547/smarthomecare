package com.czerny.smarthomecare.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Health(
    var id: String = "",
    var Place: String? = null,
    var title: String? = null,
    var name: String? = null,
    var content: String? = null,
    var note: String? = null,
    var hours: String? = null,
    var minute: String? = null,
    var date: String? = null,
    var createdTime: Long = -1,

    var familyName: String = "",

    var tag: String = "",
    val author: Author? = null

) : Parcelable