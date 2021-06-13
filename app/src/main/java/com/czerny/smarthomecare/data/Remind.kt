package com.czerny.smarthomecare.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Remind(


    var id: String = "",
    var name: String? = null,
    var hours: String? = null,
    var minute: String? = null,
    var date: String? = null,
    var content: String? = null,
    var title: String? = null,
    var createdTime: Long = -1,

    var eamil: String = "",
    var tag: String = "",
    val author: Author? = null,

    var familyName: String = ""

) : Parcelable