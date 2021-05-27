package com.czerny.smarthomecare.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Remind(
    var id: String = "",
    var name: String = "",
    var hours: String = "",
    var minute: String = "",
    var date: String = "",
    var content: String = "",
    var note: String = "",
    var createdTime : Long = -1,

    var tag: String = "",
    val author: Author? = null

) : Parcelable