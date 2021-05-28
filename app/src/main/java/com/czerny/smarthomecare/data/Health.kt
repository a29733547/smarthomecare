package com.czerny.smarthomecare.data

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Health(
    var id: String = "",
    var healthPlaceData: String = "",
    var title: String = "",
    var name: String = "",
    var content: String = "",
    var note: String = "",
    var hours: String = "",
    var minute: String = "",
    var date: String = "",
    var createdTime: Long = -1,


    var tag: String = "",
    val author: Author? = null

) : Parcelable