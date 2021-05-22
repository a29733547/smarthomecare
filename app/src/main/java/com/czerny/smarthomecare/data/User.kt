package com.czerny.smarthomecare.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
        var id: String = "",
        var profileNameData: String = "",
        var birth: String = "",
        var year: String = "",
        var weight: String = "",
        var blood: String = "",
        var genetic: String = "",
        var allergy: String = "",
        var note: String = "",
        var familyId: Int = 0
) : Parcelable