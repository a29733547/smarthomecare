package com.czerny.smarthomecare.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FamilyInfo(
    var familyId: String = "",
    var familyName: String = "",
    var createdTime: Long = 0L,
    ) : Parcelable