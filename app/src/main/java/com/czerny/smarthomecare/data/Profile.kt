package com.czerny.smarthomecare.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profile(
    var id: String = "",
    var name: String = "",
    var birth: String = "",
    var year: String = "",
    var weight: String = "",
    var blood: String = "",
    var genetic: String = "",
    var allergy: String = "",
    var note: String = "",
    var createdTime: Long = 0L,
) : Parcelable