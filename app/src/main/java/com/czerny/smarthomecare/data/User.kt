package com.czerny.smarthomecare.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var image: String = "",
    var birth: String = "",
    var year: String = "",
    var weight: String = "",
    var blood: String = "",
    var genetic: String = "",
    var allergy: String = "",
    var note: String = "",
//    var familyInfo: List<FamilyInfo> = listOf(),
    var familyId: String = "",
    var familyName: String = "",
//    var familyName: List<String> = listOf()
    var createdTime: Long = 0L,

) : Parcelable

@Parcelize
data class UserInfo(
    var userId: String = "",
    var userName: String = "",
    var userImage: String = "",
    var userEmail: String = "",
    var createdTime: Long = 0L,
) : Parcelable

@Parcelize
data class FamilyInfo(
    var familyId: String = "",
    var familyName: String = "",
    var createdTime: Long = 0L,
    ) : Parcelable