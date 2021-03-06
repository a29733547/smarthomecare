package com.czerny.smarthomecare.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var familyId: String = "",
    var familyName: String = "",
    var createdTime: Long = 0L,
) : Parcelable

