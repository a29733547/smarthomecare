package com.czerny.smarthomecare.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Remind(
    var id: String = "",
    var name: String? = "",
    var hours: String? = "",
    var minute: String? = "",
    var date: String? = "",
    var content: String? = "",
    var title: String? = "",
    var createdTime: Long = -1,

    var eamil: String = "",
    var tag: String = "",
    val author: Author? = null

) : Parcelable