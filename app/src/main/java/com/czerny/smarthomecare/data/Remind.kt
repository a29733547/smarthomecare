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
    var data: String = "",
    var content: String = "",
    var note: String = "",
    var createdTime : Long = -1
) : Parcelable{
}

//@Parcelize
//data class Remind(
//    var id: String = "",
//    var name: String = "",
//    var hours: String = "0L",
//    var minute: String = "0L",
//    var data: String = "",
//    var content: String = "",
//    var note: String = ""
//) : Parcelable