package com.czerny.smarthomecare.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfo(
    var userId: String = "",
    var userName: String = "",
    var userImage: String = "",
    var userEmail: String = "",
    var createdTime: Long = 0L,
) : Parcelable